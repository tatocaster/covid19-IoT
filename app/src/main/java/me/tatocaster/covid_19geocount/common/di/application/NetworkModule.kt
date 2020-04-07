package me.tatocaster.covid_19geocount.common.di.application

import dagger.Module
import dagger.Provides
import me.tatocaster.covid_19geocount.source.remote.RequestManager
import me.tatocaster.covid_19geocount.source.remote.RetrofitService
import me.tatocaster.covid_19geocount.source.remote.RetrofitWrapper
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun retrofitService(): RetrofitService = RetrofitWrapper(RequestManager().service)
}