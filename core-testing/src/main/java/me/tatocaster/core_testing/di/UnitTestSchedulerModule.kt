package me.tatocaster.core_testing.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.tatocaster.core.source.BaseSchedulerProvider
import me.tatocaster.core_testing.utils.TrampolineTestSchedulerProvider

@Module
object UnitTestSchedulerModule {

    @Reusable
    @Provides
    fun scheduler(): BaseSchedulerProvider = TrampolineTestSchedulerProvider()
}