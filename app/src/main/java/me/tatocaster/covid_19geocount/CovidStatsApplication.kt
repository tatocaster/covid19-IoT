package me.tatocaster.covid_19geocount

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.WorkManager
import me.tatocaster.covid_19geocount.common.di.application.ApplicationComponent
import me.tatocaster.covid_19geocount.common.di.application.ApplicationModule
import me.tatocaster.covid_19geocount.common.di.application.DaggerApplicationComponent
import timber.log.Timber

open class CovidStatsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        applicationComponent = createAppComponent()

        context = applicationContext

        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement) = TAG
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
                .setMinimumLoggingLevel(Log.INFO)
                .setWorkerFactory(applicationComponent.factory())
                .build()
        )
    }

    private fun createAppComponent(): ApplicationComponent =
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()


    companion object {
        @SuppressLint("StaticFieldLeak")
        //this is app context and setting is performed only in onCreate, it's not supposed to leak
        lateinit var context: Context
        lateinit var applicationComponent: ApplicationComponent
        lateinit var config: ApplicationConfig

        const val TAG = "covid-app"
    }
}