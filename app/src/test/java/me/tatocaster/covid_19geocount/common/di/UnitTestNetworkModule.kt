package me.tatocaster.covid_19geocount.common.di

import dagger.Module
import dagger.Provides
import me.tatocaster.covid_19geocount.source.remote.FakeRetrofitService
import me.tatocaster.covid_19geocount.source.remote.RetrofitService
import javax.inject.Singleton

@Module
object UnitTestNetworkModule {

    @Singleton
    @Provides
    fun retrofitService(): RetrofitService = FakeRetrofitService()
}