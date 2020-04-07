package me.tatocaster.covid_19geocount.common

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider

class CovidStatsWorkerFactory @Inject constructor(
    private val workerFactories: Map<Class<out ListenableWorker>,
            @JvmSuppressWildcards Provider<ChildWorkerFactory>>
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        val foundEntry =
            workerFactories.entries.find { Class.forName(workerClassName).isAssignableFrom(it.key) }
        return if (foundEntry != null) {
            val factoryProvider = foundEntry.value
            factoryProvider.get().create(appContext, workerParameters)
        } else {
            throw IllegalArgumentException("Entry not found")
        }
    }

    interface ChildWorkerFactory {
        fun create(appContext: Context, params: WorkerParameters): ListenableWorker
    }
}