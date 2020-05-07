package me.tatocaster.covidstats.interactors

import io.reactivex.Single
import me.tatocaster.core.entities.CovidCase

interface CovidStatsInteractor {
    fun getStatsForGeorgia(): Single<CovidCase>

    companion object {
        const val SELECTED_COUNTRY = "GEO"
    }
}