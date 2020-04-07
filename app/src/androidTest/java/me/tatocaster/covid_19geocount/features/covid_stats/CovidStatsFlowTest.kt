package me.tatocaster.covid_19geocount.features.covid_stats

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import me.tatocaster.covid_19geocount.InstrumentedTestApplication
import me.tatocaster.covid_19geocount.R
import me.tatocaster.covid_19geocount.common.BaseInstrumentedTest
import me.tatocaster.covid_19geocount.features.covid_stats.interactors.CovidStatsInteractor
import org.hamcrest.CoreMatchers.*
import org.junit.Test
import javax.inject.Inject

class CovidStatsFlowTest : BaseInstrumentedTest() {
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

        // TODO: change this with idling resource
        Thread.sleep(200)

        Espresso.onView(withId(R.id.tvTotalDeathCount))
            .check(matches(not(withText(containsString("Test")))))
    }

}