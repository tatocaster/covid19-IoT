package me.tatocaster.covidstats.interactors

import io.reactivex.Single
import me.tatocaster.core.entities.CovidCase
import me.tatocaster.covidstats.repositories.CovidStatsRepository

class CovidStatsInteractorImpl(private val repository: CovidStatsRepository) :
    CovidStatsInteractor {

    override fun getStatsForGeorgia(): Single<CovidCase> =
        repository
            .getStats()
    /*.filter {
        it.country.toLowerCase() == SELECTED_COUNTRY
    }*/
}