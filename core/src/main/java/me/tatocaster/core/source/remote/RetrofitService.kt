package me.tatocaster.core.source.remote

import io.reactivex.Single
import me.tatocaster.core.source.remote.schema.CovidCaseCountryResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("geo/")
    fun getCovidStats(): Single<Response<CovidCaseCountryResponse>>

}