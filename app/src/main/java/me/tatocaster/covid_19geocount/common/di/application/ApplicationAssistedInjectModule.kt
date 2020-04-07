package me.tatocaster.covid_19geocount.common.di.application

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@Module(
    includes = [
        AssistedInject_ApplicationAssistedInjectModule::class
    ]
)
@AssistedModule
interface ApplicationAssistedInjectModule