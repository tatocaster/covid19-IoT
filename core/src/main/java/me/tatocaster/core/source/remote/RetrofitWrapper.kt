package me.tatocaster.core.source.remote

import io.reactivex.Single
import me.tatocaster.core.source.BaseSchedulerProvider
import me.tatocaster.core.source.remote.schema.CovidCaseCountryResponse
import retrofit2.Response

class RetrofitWrapper(
    private val retrofitService: RetrofitService,
    private val scheduler: BaseSchedulerProvider
) : RetrofitService {

    override fun getCovidStats(): Single<Response<CovidCaseCountryResponse>> {
        return retrofitService
            .getCovidStats()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())
    }

}