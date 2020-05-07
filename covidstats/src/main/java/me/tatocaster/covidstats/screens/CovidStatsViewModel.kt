package me.tatocaster.covidstats.screens

import android.content.Context
import androidx.work.*
import me.tatocaster.core.base.*
import me.tatocaster.core.entities.CovidCase
import me.tatocaster.covidstats.interactors.CovidStatsInteractor
import me.tatocaster.covidstats.workers.CovidStatsWorker
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

class CovidStatsViewModel(
    appContext: Context,
    private val interactor: CovidStatsInteractor
) : BaseStateViewModel() {
    // this will not leak, because of Application Context is aware of lifecycle, forget AndroidViewModel
    private val workManager = WorkManager.getInstance(appContext)

    init {
        val scheduledJob = scheduleJob()
        getWorkInfo(scheduledJob.id)
        getCovidCases()
    }

    fun getCovidCases() {
        interactor.getStatsForGeorgia()
            .doOnSubscribe {
                state.value = JustLoading
            }
            .subscribeManaged({
                Timber.d("case %s", it)
                state.value = CasesLoaded(it)
            }, {
                Timber.e(it, "error from viewmodel")
                state.value = JustError(it)
            })
    }

    // screen will be always on the same fragment, so we can schedule from this viewmodel
    private fun scheduleJob(): PeriodicWorkRequest {
        workManager.cancelAllWorkByTag(UNIQUE_WORK_ID)
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val jobRequest =
            PeriodicWorkRequestBuilder<CovidStatsWorker>(20, TimeUnit.MINUTES, 15, TimeUnit.MINUTES)
                .addTag(UNIQUE_WORK_ID)
                .setConstraints(constraints)
                .build()

        workManager
            .enqueueUniquePeriodicWork(
                "covidstats",
                ExistingPeriodicWorkPolicy.REPLACE,
                jobRequest
            )

        return jobRequest
    }

    private fun getWorkInfo(id: UUID) {
        workManager.getWorkInfoByIdLiveData(id)
            .observeForever { workInfo ->
                workInfo?.let {
                    Timber.d("work info state from viewmodel ${workInfo.state}")
                    when (workInfo.state) {
                        WorkInfo.State.SUCCEEDED -> {
                            /*
                        periodic requests are working like this
                        ENQUEUED - RUNNING - ENQUEUED

                        One time requests
                        ENQUEUED - RUNNING - SUCCEEDED

                        That's why we use RUNNING state for monitoring
                         */
                        }
                        WorkInfo.State.RUNNING -> {
                            state.value = JustLoading
                            Timber.d("data from work manager")
                            getCovidCases()
                        }
                        WorkInfo.State.BLOCKED -> {
                            Timber.e("Error in work manager")
                            state.value = JustUnknownError
                        }
                        else -> {
                            state.value = JustInitial
                        }
                    }
                }
            }
    }

    companion object {
        private const val UNIQUE_WORK_ID: String = "CovidStatsScheduler"
    }

    override fun onCleared() {
        super.onCleared()
        workManager.cancelAllWorkByTag(UNIQUE_WORK_ID)
    }

    class CasesLoaded(val payload: CovidCase) : State<Payload>()
}