package me.tatocaster.covid_19geocount

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import me.tatocaster.covid_19geocount.CovidStatsApplication.Companion.config
import me.tatocaster.covid_19geocount.common.di.DaggerUnitTestApplicationComponent
import me.tatocaster.covid_19geocount.common.di.UnitTestApplicationComponent
import me.tatocaster.covid_19geocount.common.di.application.ApplicationModule

class UnitTestApplication : Application() {
    override fun onCreate() {
        testApplicationComponent = createAppComponent()

        context = applicationContext
        config = ApplicationConfig(true)

        initWorkManager()
    }

    private fun createAppComponent(): UnitTestApplicationComponent {
        return DaggerUnitTestApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    private fun initWorkManager() {
        WorkManagerTestInitHelper.initializeTestWorkManager(
            context,
            Configuration.Builder()
                .setMinimumLoggingLevel(Log.INFO)
                .setExecutor(SynchronousExecutor())
                .build()
        )
    }

    companion object {
        lateinit var context: Context
        lateinit var testApplicationComponent: UnitTestApplicationComponent
    }

}