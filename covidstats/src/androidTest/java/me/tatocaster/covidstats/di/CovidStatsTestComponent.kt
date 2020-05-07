package me.tatocaster.covidstats.di

import dagger.Component
import me.tatocaster.core.di.application.CoreComponent
import me.tatocaster.covidstats.CovidStatsFlowTest
import me.tatocaster.covidstats.CovidStatsTest

@CovidStatsFeatureScope
@Component(
    dependencies = [
        CoreComponent::class
    ],
    modules = [
        CovidStatsModule::class
    ]
)
interface CovidStatsTestComponent {
    @Component.Factory
    interface Factory {
        fun create(
            covidStatsModule: CovidStatsModule,
            coreComponent: CoreComponent
        ): CovidStatsTestComponent
    }

    fun inject(covidStatsTest: CovidStatsTest)
    fun inject(covidStatsFlowTest: CovidStatsFlowTest)
}