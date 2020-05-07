package me.tatocaster.core.di.application

import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.tatocaster.core.ChildWorkerFactory
import me.tatocaster.core.CovidStatsWorkerFactory
import javax.inject.Provider
/*import me.tatocaster.core.di.application.AssistedInject_ApplicationAssistedInjectModule

@Module(
    includes = [
        AssistedInject_ApplicationAssistedInjectModule::class
    ]
)
@AssistedModule
interface ApplicationAssistedInjectModule*/

@Module
object ApplicationAssistedInjectModule {
    @Provides
    @JvmStatic
    fun bindCovidStatsWorkerFactory(
        workerFactories: Map<Class<out ListenableWorker>,
                @JvmSuppressWildcards Provider<ChildWorkerFactory>>
    ): CovidStatsWorkerFactory = CovidStatsWorkerFactory(workerFactories)
}