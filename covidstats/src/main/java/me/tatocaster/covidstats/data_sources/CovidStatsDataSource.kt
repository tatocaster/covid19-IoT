package me.tatocaster.covidstats.data_sources

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import me.tatocaster.core.source.local.covidstats.CovidStatDbEntity
import me.tatocaster.core.source.remote.schema.CovidCaseCountryResponse

interface CovidStatsDataSource {

    interface Remote {
        fun getStatsForCountries(): Single<CovidCaseCountryResponse>
    }

    interface Local {
        fun getStats(): Maybe<CovidStatDbEntity>
        fun update(covidStat: CovidStatDbEntity): Completable
    }
}