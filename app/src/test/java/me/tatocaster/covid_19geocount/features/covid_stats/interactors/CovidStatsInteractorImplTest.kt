package me.tatocaster.covid_19geocount.features.covid_stats.interactors

import me.tatocaster.covid_19geocount.UnitTestApplication
import me.tatocaster.covid_19geocount.common.BaseUnitTest
import me.tatocaster.covid_19geocount.utils.assertCompleteAndDispose
import org.junit.Test
import javax.inject.Inject

class CovidStatsInteractorImplTest : BaseUnitTest() {
    @Inject
    lateinit var covidStatsInteractorImpl: CovidStatsInteractor

    override fun beforeEachTest() {
        UnitTestApplication.testApplicationComponent.inject(this)
    }

    @Test
    fun getStatsForGeorgia() {
        covidStatsInteractorImpl.getStatsForGeorgia()
            .map { it.country }
            .test()
            .assertValue("GEO")
            .assertCompleteAndDispose()
    }
}