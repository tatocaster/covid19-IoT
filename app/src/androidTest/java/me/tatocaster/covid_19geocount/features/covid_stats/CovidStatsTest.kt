package me.tatocaster.covid_19geocount.features.covid_stats

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import me.tatocaster.covid_19geocount.InstrumentedTestApplication
import me.tatocaster.covid_19geocount.common.BaseInstrumentedTest
import me.tatocaster.covid_19geocount.features.covid_stats.interactors.CovidStatsInteractor
import org.hamcrest.CoreMatchers.`is`
import org.junit.Test
import javax.inject.Inject

class CovidStatsTest : BaseInstrumentedTest() {
    @Inject
    lateinit var interactor: CovidStatsInteractor

    override fun before() {
        super.before()
        InstrumentedTestApplication.testApplicationComponent.inject(this)
    }

    @Test
    fun areNumbersLoaded() {
        val stats = interactor.getStatsForGeorgia().blockingGet()
        assertThat(stats.country, `is`("GEO"))
    }

}