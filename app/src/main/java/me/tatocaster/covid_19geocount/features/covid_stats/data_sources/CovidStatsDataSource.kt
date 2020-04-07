package me.tatocaster.covid_19geocount.features.covid_stats.data_sources

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import me.tatocaster.covid_19geocount.source.local.covidstats.CovidStatDbEntity
import me.tatocaster.covid_19geocount.source.remote.schema.CovidCaseCountryResponse

interface CovidStatsDataSource {

    interface Remote {
        fun getStatsForCountries(): Single<CovidCaseCountryResponse>
    }

    interface Local {
        fun getStats(): Maybe<CovidStatDbEntity>
        fun update(covidStat: CovidStatDbEntity): Completable
    }
}