package me.tatocaster.covid_19geocount.common.di.presentation

import dagger.Subcomponent
import me.tatocaster.covid_19geocount.features.covid_stats.screens.CovidStatsFragment

@Subcomponent(
    modules = [
        PresentationModule::class,
        ViewModelModule::class
    ]
)
interface PresentationComponent {
    fun inject(covidStatsFragment: CovidStatsFragment)
}