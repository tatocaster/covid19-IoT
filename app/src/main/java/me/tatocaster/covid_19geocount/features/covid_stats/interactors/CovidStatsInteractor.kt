package me.tatocaster.covid_19geocount.features.covid_stats.interactors

import io.reactivex.Completable
import io.reactivex.Single
import me.tatocaster.covid_19geocount.common.entities.CovidCase

interface CovidStatsInteractor {
    fun getStatsForGeorgia(): Single<CovidCase>

    companion object {
        const val SELECTED_COUNTRY = "GEO"
    }
}