package me.tatocaster.core_testing

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import me.tatocaster.core_testing.di.DaggerUnitTestAppComponentImpl
import me.tatocaster.core_testing.di.UnitTestAppComponentImpl

class UnitTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        testApplicationComponent =
            DaggerUnitTestAppComponentImpl.factory().create(applicationContext)
        initWorkManager()
    }

    private fun initWorkManager() {
        WorkManagerTestInitHelper.initializeTestWorkManager(
            applicationContext,
            Configuration.Builder()
                .setMinimumLoggingLevel(Log.INFO)
                .setExecutor(SynchronousExecutor())
                .build()
        )
    }

    companion object {
        lateinit var context: Context
        lateinit var testApplicationComponent: UnitTestAppComponentImpl
    }
}