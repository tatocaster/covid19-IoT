package me.tatocaster.covidstats.workers

import android.content.Context
import androidx.annotation.Keep
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import me.tatocaster.core.ChildWorkerFactory
import me.tatocaster.core.CovidStatsWorkerFactory
import me.tatocaster.core.di.qualifierscopes.ApplicationContext
import me.tatocaster.core.source.remote.RetrofitService
import me.tatocaster.covidstats.interactors.CovidStatsInteractor
import javax.inject.Inject

/*class CovidStatsWorker @AssistedInject constructor(
    @Assisted arg0: Context,
    @Assisted arg1: WorkerParameters,
    retrofitService: RetrofitService
) :
    Worker(arg0, arg1) {
    override fun doWork(): Result {
//            getStatsWithoutCache from network

//            interactor.save(covidCase).blockingAwait()

        // do some work synchronously


        // mostly this is for timer purposes

        return Result.success()
    }

    @AssistedInject.Factory
    interface Factory : ChildWorkerFactory
}*/


class CovidStatsWorker @Inject constructor(
    arg0: Context,
    arg1: WorkerParameters,
    private val retrofitService: RetrofitService
) :
    Worker(arg0, arg1) {
    override fun doWork(): Result {
//            getStatsWithoutCache from network

//            interactor.save(covidCase).blockingAwait()

        // do some work synchronously


        // mostly this is for timer purposes

        return Result.success()
    }

    class Factory @Inject constructor(private val retrofitService: RetrofitService) :
        ChildWorkerFactory {
        override fun create(appContext: Context, params: WorkerParameters): Worker {
            return CovidStatsWorker(appContext, params, retrofitService)
        }
    }
}