package me.tatocaster.covid_19geocount.common.di

import dagger.Module
import dagger.Provides
import me.tatocaster.covid_19geocount.source.remote.MockRetrofitService
import me.tatocaster.covid_19geocount.source.remote.RequestManager
import me.tatocaster.covid_19geocount.source.remote.RetrofitService
import javax.inject.Singleton

@Module
object InstrumentedTestNetworkModule {

    @Singleton
    @Provides
    fun retrofitService(): RetrofitService = MockRetrofitService(RequestManager().service)
}