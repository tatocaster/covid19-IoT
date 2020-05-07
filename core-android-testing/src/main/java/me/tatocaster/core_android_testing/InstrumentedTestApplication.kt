package me.tatocaster.core_android_testing

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import me.tatocaster.core.di.application.CoreComponent
import me.tatocaster.core.di.application.CoreComponentProvider
import me.tatocaster.core_android_testing.di.DaggerInstrumentedTestAppComponentImpl

class InstrumentedTestApplication : Application(), CoreComponentProvider {
    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        appComponent = DaggerInstrumentedTestAppComponentImpl
            .factory()
            .create(applicationContext)

        initWorkManager()
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
        lateinit var appComponent: CoreComponent
    }

    override val coreComponent: CoreComponent
        get() = appComponent

}