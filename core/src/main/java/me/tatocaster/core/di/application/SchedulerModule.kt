package me.tatocaster.core.di.application

import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.tatocaster.core.source.BaseSchedulerProvider
import me.tatocaster.core.source.SchedulerProvider

@Module
object SchedulerModule {

    @Provides
    @JvmStatic
    @Reusable
    fun scheduler(): BaseSchedulerProvider {
        return SchedulerProvider()
    }
}