package me.tatocaster.covid_19geocount

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.WorkManager
import me.tatocaster.core.ApplicationConfig
import me.tatocaster.covid_19geocount.BuildConfig
import me.tatocaster.core.di.application.CoreComponentProvider
import timber.log.Timber

open class CovidStatsApplication : Application(), CoreComponentProvider {
    override val coreComponent: AppComponentImpl by lazy {
        DaggerAppComponentImpl.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement) =
                    TAG
            })
        }
        config = ApplicationConfig()

        initWorkManager()
        Timber.d("init app")
    }

    private fun initWorkManager() {
        WorkManager.initialize(
            this,
            Configuration.Builder()
                .setMinimumLoggingLevel(Log.DEBUG)
                .setWorkerFactory(coreComponent.workerFactory)
                .build()
        )
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        //this is app context and setting is performed only in onCreate, it's not supposed to leak
        lateinit var context: Context

        lateinit var config: ApplicationConfig

        const val TAG = "covid-app"
    }
}