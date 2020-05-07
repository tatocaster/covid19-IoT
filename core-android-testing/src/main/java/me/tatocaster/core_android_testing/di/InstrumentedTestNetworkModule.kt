package me.tatocaster.core_android_testing.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.tatocaster.core.source.remote.RequestManager
import me.tatocaster.core.source.remote.RetrofitService
import me.tatocaster.core_android_testing.MockRetrofitService

@Module
object InstrumentedTestNetworkModule {

    @Reusable
    @Provides
    fun retrofitService(): RetrofitService =
        MockRetrofitService(RequestManager().service)
}