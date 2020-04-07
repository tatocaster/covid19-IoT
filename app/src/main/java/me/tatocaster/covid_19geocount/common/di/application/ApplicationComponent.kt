package me.tatocaster.covid_19geocount.common.di.application

import dagger.Component
import me.tatocaster.covid_19geocount.common.CovidStatsWorkerFactory
import me.tatocaster.covid_19geocount.common.di.presentation.PresentationComponent
import me.tatocaster.covid_19geocount.common.di.presentation.PresentationModule
import me.tatocaster.covid_19geocount.features.covid_stats.CovidStatsModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ApplicationAssistedInjectModule::class,
        NetworkModule::class,
        CovidStatsModule::class,
        DatabaseModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent

    fun factory(): CovidStatsWorkerFactory
}