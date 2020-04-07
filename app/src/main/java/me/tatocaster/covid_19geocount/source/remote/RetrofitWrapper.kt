package me.tatocaster.covid_19geocount.source.remote

import io.reactivex.Single
import me.tatocaster.covid_19geocount.source.applySchedulers
import me.tatocaster.covid_19geocount.source.remote.schema.CovidCaseCountryResponse
import retrofit2.Response

class RetrofitWrapper(private val retrofitService: RetrofitService) : RetrofitService {

    override fun getCovidStats(): Single<Response<CovidCaseCountryResponse>> {
        return retrofitService
            .getCovidStats()
            .applySchedulers()
    }

}