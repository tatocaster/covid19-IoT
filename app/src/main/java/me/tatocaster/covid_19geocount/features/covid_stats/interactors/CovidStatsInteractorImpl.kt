package me.tatocaster.covid_19geocount.features.covid_stats.interactors

import io.reactivex.Single
import me.tatocaster.covid_19geocount.common.entities.CovidCase
import me.tatocaster.covid_19geocount.features.covid_stats.repositories.CovidStatsRepository

class CovidStatsInteractorImpl(private val repository: CovidStatsRepository) :
    CovidStatsInteractor {

    override fun getStatsForGeorgia(): Single<CovidCase> =
        repository
            .getStats()
    /*.filter {
        it.country.toLowerCase() == SELECTED_COUNTRY
    }*/
}