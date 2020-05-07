package me.tatocaster.core_android_testing.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.tatocaster.core.source.BaseSchedulerProvider
import me.tatocaster.core_android_testing.utils.TrampolineSchedulerProvider

@Module
object InstrumentedTestSchedulerModule {

    @Reusable
    @Provides
    fun scheduler(): BaseSchedulerProvider = TrampolineSchedulerProvider()
}