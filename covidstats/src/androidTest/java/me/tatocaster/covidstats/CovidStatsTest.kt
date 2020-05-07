package me.tatocaster.covidstats

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import me.tatocaster.core_android_testing.InstrumentedTestApplication
import me.tatocaster.covidstats.di.CovidStatsModule
import me.tatocaster.covidstats.di.DaggerCovidStatsTestComponent
import me.tatocaster.covidstats.interactors.CovidStatsInteractor
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class CovidStatsTest {

    @Inject
    lateinit var interactor: CovidStatsInteractor

    @Before
    fun before() {
        DaggerCovidStatsTestComponent.factory()
            .create(
                CovidStatsModule,
                InstrumentedTestApplication.appComponent
            )
            .inject(this)
    }

    @Test
    fun testAreNumbersLoaded() {
        val stats = interactor.getStatsForGeorgia().blockingGet()
        assertThat(stats.country, `is`("GEO"))
    }

}