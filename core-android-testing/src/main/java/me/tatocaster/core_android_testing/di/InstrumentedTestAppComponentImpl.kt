package me.tatocaster.core_android_testing.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.tatocaster.core.di.application.CoreComponent
import me.tatocaster.core.di.application.DatabaseModule
import me.tatocaster.core.di.qualifierscopes.ApplicationContext
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        InstrumentedTestNetworkModule::class,
        DatabaseModule::class,
        InstrumentedTestSchedulerModule::class
    ]
)
interface InstrumentedTestAppComponentImpl : CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @ApplicationContext context: Context): InstrumentedTestAppComponentImpl
    }
}