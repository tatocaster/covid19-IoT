package me.tatocaster.covid_19geocount

import android.os.Bundle
import me.tatocaster.covid_19geocount.R
import me.tatocaster.core.base.BaseActivity
import me.tatocaster.covidstats.screens.CovidStatsFragment

/**
 * Skeleton of an Android Things activity.
 *
 * Android Things peripheral APIs are accessible through the PeripheralManager
 * For example, the snippet below will open a GPIO pin and set it to HIGH:
 *
 * val manager = PeripheralManager.getInstance()
 * val gpio = manager.openGpio("BCM6").apply {
 *     setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW)
 * }
 * gpio.value = true
 *
 * You can find additional examples on GitHub: https://github.com/androidthings
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            showStatsFragment()
    }

    private fun showStatsFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, CovidStatsFragment.newInstance())
            .commit()
    }
}
