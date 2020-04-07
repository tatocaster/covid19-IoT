package me.tatocaster.covid_19geocount.common.di.application

import androidx.work.ListenableWorker
import dagger.MapKey
import dagger.Module
import dagger.Provides
import me.tatocaster.covid_19geocount.common.CovidStatsWorkerFactory
import javax.inject.Provider
import kotlin.reflect.KClass

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class WorkerKey(val value: KClass<out ListenableWorker>)