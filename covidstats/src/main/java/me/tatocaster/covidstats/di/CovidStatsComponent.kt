package me.tatocaster.covidstats.di

import dagger.Component
import me.tatocaster.core.di.application.CoreComponent
import me.tatocaster.core.di.presentation.PresentationModule
import me.tatocaster.covidstats.screens.CovidStatsFragment

@CovidStatsFeatureScope
@Component(
    dependencies = [
        CoreComponent::class
    ],
    modules = [
        PresentationModule::class,
        CovidStatsModule::class
    ]
)
interface CovidStatsComponent {
    @Component.Factory
    interface Factory {
        fun create(
            presentationModule: PresentationModule,
            covidStatsModule: CovidStatsModule,
            coreComponent: CoreComponent
        ): CovidStatsComponent
    }

    fun inject(covidStatsFragment: CovidStatsFragment)
}