package me.tatocaster.core.di.qualifierscopes

import androidx.work.ListenableWorker
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class WorkerKey(val value: KClass<out ListenableWorker>)