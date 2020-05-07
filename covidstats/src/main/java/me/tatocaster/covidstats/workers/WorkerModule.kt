package me.tatocaster.covidstats.workers

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.multibindings.IntoMap
import me.tatocaster.core.ChildWorkerFactory
import me.tatocaster.core.di.qualifierscopes.WorkerKey

@Module
interface WorkerModule {
    @Binds
    @IntoMap
    @WorkerKey(CovidStatsWorker::class)
    fun covidStatsWorker(factory: CovidStatsWorker.Factory): ChildWorkerFactory
}