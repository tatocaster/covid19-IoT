package me.tatocaster.core_testing

import io.reactivex.Single
import me.tatocaster.core.source.remote.RetrofitService
import me.tatocaster.core.source.remote.schema.CovidCaseCountryResponse
import retrofit2.Response

class FakeRetrofitService : RetrofitService {
    override fun getCovidStats(): Single<Response<CovidCaseCountryResponse>> {
        return fakeSuccessSingle(
            CovidCaseCountryResponse(
                "GEO",
                "06.04.2020",
                0,
                0,
                2,
                190,
                140,
                2,
                40
            )
        )
    }

    private fun <T> fakeSuccessSingle(result: T): Single<Response<T>> =
        Single.just(Response.success(result))
}