package me.tatocaster.core.di.qualifierscopes;

import javax.inject.Scope;

import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope {
}