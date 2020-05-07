package me.tatocaster.covid_19geocount

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.tatocaster.core.CovidStatsWorkerFactory
import me.tatocaster.core.di.application.*
import me.tatocaster.core.di.qualifierscopes.AppScope
import me.tatocaster.core.di.qualifierscopes.ApplicationContext
import me.tatocaster.covidstats.workers.WorkerModule

@AppScope
@Component(
    modules = [
        ApplicationAssistedInjectModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        SchedulerModule::class,
        WorkerModule::class
    ]
)
interface AppComponentImpl : CoreComponent {
    val workerFactory: CovidStatsWorkerFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @ApplicationContext applicationContext: Context): AppComponentImpl
    }
}

