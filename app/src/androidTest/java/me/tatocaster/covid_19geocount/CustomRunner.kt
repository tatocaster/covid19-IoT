package me.tatocaster.covid_19geocount

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class CustomRunner : AndroidJUnitRunner() {

    @Throws(Exception::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, InstrumentedTestApplication::class.java.name, context)
    }
}