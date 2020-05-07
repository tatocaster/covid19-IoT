package me.tatocaster.core.di.application

import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.tatocaster.core.source.BaseSchedulerProvider
import me.tatocaster.core.source.remote.RequestManager
import me.tatocaster.core.source.remote.RetrofitService
import me.tatocaster.core.source.remote.RetrofitWrapper

@Module
object NetworkModule {

    @Reusable
    @Provides
    @JvmStatic
    fun retrofitService(scheduler: BaseSchedulerProvider): RetrofitService =
        RetrofitWrapper(RequestManager().service, scheduler)
}