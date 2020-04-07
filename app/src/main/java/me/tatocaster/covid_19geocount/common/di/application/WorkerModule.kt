package me.tatocaster.covid_19geocount.common.di.application

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.tatocaster.covid_19geocount.common.CovidStatsWorkerFactory
import me.tatocaster.covid_19geocount.features.covid_stats.workers.CovidStatsWorker

@Module
interface WorkerModule {
    @Binds
    @IntoMap
    @WorkerKey(CovidStatsWorker::class)
    fun covidStatsWorker(factory: CovidStatsWorker.Factory): CovidStatsWorkerFactory.ChildWorkerFactory
}
