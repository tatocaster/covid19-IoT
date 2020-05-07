package me.tatocaster.core_testing

import android.os.Build
import androidx.test.core.app.ApplicationProvider
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


// Custom runner allows us set config in one place instead of setting it in each test class.
class CovidStatsRobolectricUnitTestRunner @Throws(Exception::class)
constructor(klass: Class<*>) : RobolectricTestRunner(klass) {


    override fun buildGlobalConfig(): Config {
        return Config.Builder()
            .setSdk(Build.VERSION_CODES.P) // Android SDK 29 requires Java 9 (have Java 8)
            .setApplication(UnitTestApplication::class.java)
            .build()
    }

    companion object {
        fun covidStatsApplication(): UnitTestApplication {
            return ApplicationProvider.getApplicationContext()
        }
    }
}