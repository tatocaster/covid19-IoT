package me.tatocaster.covid_19geocount.features.covid_stats.repositories

import io.reactivex.Completable
import io.reactivex.Single
import me.tatocaster.covid_19geocount.common.entities.CovidCase

interface CovidStatsRepository {
    fun getStats(): Single<CovidCase>
    fun updateCache(covidCase: CovidCase): Completable
}