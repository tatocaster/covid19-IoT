package me.tatocaster.covid_19geocount.common.di

import dagger.Component
import me.tatocaster.covid_19geocount.common.di.application.ApplicationAssistedInjectModule
import me.tatocaster.covid_19geocount.common.di.application.ApplicationModule
import me.tatocaster.covid_19geocount.common.di.application.DatabaseModule
import me.tatocaster.covid_19geocount.features.covid_stats.CovidStatsFlowTest
import me.tatocaster.covid_19geocount.features.covid_stats.CovidStatsModule
import me.tatocaster.covid_19geocount.features.covid_stats.CovidStatsTest
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ApplicationAssistedInjectModule::class,
        InstrumentedTestNetworkModule::class,
        CovidStatsModule::class,
        DatabaseModule::class
    ]
)
interface InstrumentedTestApplicationComponent {
    fun inject(covidStatsTest: CovidStatsTest)
    fun inject(covidStatsFlowTest: CovidStatsFlowTest)
}