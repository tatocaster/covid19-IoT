package me.tatocaster.covidstats.data_sources

import io.reactivex.Single
import me.tatocaster.core.source.remote.RetrofitService
import me.tatocaster.core.source.remote.schema.CovidCaseCountryResponse
import me.tatocaster.core.source.getResponseBody

class CovidStatsRemoteDataSource(private val retrofitService: RetrofitService) :
    CovidStatsDataSource.Remote {
    override fun getStatsForCountries(): Single<CovidCaseCountryResponse> =
        retrofitService
            .getCovidStats()
            .getResponseBody()

}