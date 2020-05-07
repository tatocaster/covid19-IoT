package me.tatocaster.core_testing.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.tatocaster.core.source.remote.RetrofitService
import me.tatocaster.core_testing.FakeRetrofitService

@Module
object UnitTestNetworkModule {

    @Reusable
    @JvmStatic
    @Provides
    fun retrofitService(): RetrofitService =
        FakeRetrofitService()
}