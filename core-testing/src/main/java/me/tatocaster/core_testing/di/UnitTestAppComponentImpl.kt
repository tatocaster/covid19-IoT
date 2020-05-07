package me.tatocaster.core_testing.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.tatocaster.core.di.application.CoreComponent
import me.tatocaster.core.di.qualifierscopes.ApplicationContext
import me.tatocaster.core_testing.source.local.DatabaseTest
import me.tatocaster.core_testing.source.remote.RemoteApiTest
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        UnitTestDatabaseModule::class,
        UnitTestNetworkModule::class,
        UnitTestSchedulerModule::class
    ]
)
interface UnitTestAppComponentImpl : CoreComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @ApplicationContext applicationContext: Context): UnitTestAppComponentImpl
    }

    fun inject(remoteApiTest: RemoteApiTest)
    fun inject(databaseTest: DatabaseTest)

}