package me.tatocaster.covid_19geocount.features.covid_stats.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import me.tatocaster.covid_19geocount.common.CovidStatsWorkerFactory
import me.tatocaster.covid_19geocount.features.covid_stats.interactors.CovidStatsInteractor
import timber.log.Timber
import java.lang.RuntimeException

class CovidStatsWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val interactor: CovidStatsInteractor
) :
    Worker(appContext, params) {
    override fun doWork(): Result {
//            getStatsWithoutCache from network

//            interactor.save(covidCase).blockingAwait()

        // do some work synchronously


        // mostly this is for timer purposes

        return Result.success()
    }

    @AssistedInject.Factory
    interface Factory : CovidStatsWorkerFactory.ChildWorkerFactory
}