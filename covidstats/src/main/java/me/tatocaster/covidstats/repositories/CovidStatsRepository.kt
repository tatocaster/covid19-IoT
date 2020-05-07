package me.tatocaster.covidstats.repositories

import io.reactivex.Completable
import io.reactivex.Single
import me.tatocaster.core.entities.CovidCase

interface CovidStatsRepository {
    fun getStats(): Single<CovidCase>
    fun updateCache(covidCase: CovidCase): Completable
}