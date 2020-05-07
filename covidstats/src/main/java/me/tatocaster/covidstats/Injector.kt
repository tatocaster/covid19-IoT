package me.tatocaster.covidstats

import me.tatocaster.core.di.application.injector
import me.tatocaster.covidstats.di.DaggerCovidStatsComponent
import me.tatocaster.core.di.presentation.PresentationModule
import me.tatocaster.covidstats.di.CovidStatsModule
import me.tatocaster.covidstats.screens.CovidStatsFragment

fun CovidStatsFragment.inject() {
    DaggerCovidStatsComponent.factory()
        .create(
            PresentationModule(this.requireActivity()),
            CovidStatsModule,
            injector
        )
        .inject(this)
}