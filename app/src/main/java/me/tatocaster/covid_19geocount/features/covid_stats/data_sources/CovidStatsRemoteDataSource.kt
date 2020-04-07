package me.tatocaster.covid_19geocount.features.covid_stats.data_sources

import io.reactivex.Single
import me.tatocaster.covid_19geocount.source.remote.RetrofitService
import me.tatocaster.covid_19geocount.source.remote.schema.CovidCaseCountryResponse
import me.tatocaster.covid_19geocount.source.getResponseBody

class CovidStatsRemoteDataSource(private val retrofitService: RetrofitService) :
    CovidStatsDataSource.Remote {
    override fun getStatsForCountries(): Single<CovidCaseCountryResponse> =
        retrofitService
            .getCovidStats()
            .getResponseBody()

}