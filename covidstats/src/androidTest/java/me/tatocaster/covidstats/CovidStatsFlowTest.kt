package me.tatocaster.covidstats

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import me.tatocaster.core_android_testing.InstrumentedTestApplication
import me.tatocaster.core_android_testing.base.BaseInstrumentedTest
import me.tatocaster.covidstats.di.CovidStatsModule
import me.tatocaster.covidstats.di.DaggerCovidStatsTestComponent
import me.tatocaster.covidstats.interactors.CovidStatsInteractor
import me.tatocaster.covidstats.screens.CovidStatsFragment
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import javax.inject.Inject

class CovidStatsFlowTest : BaseInstrumentedTest() {
    @Inject
    lateinit var interactor: CovidStatsInteractor

    override fun onPrepareInjection() {
        DaggerCovidStatsTestComponent.factory()
            .create(
                CovidStatsModule,
                InstrumentedTestApplication.appComponent
            )
            .inject(this)
    }

    override fun onRequestFragment() = CovidStatsFragment.newInstance()

    @Test
    fun testAreNumbersLoaded() {
//        val stats = interactor.getStatsForGeorgia().blockingGet()
//        assertThat(stats.country, `is`("GEO"))
        onView(withId(R.id.bRefreshStats)).perform(click())
        onView(withId(R.id.tvTotalDeathCount))
            .check(matches(not(withText(""))))
    }

}