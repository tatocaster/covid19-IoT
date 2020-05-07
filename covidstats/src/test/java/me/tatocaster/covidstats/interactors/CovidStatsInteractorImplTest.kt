package me.tatocaster.covidstats.interactors

import me.tatocaster.core_testing.UnitTestApplication
import me.tatocaster.core_testing.base.BaseUnitTest
import me.tatocaster.core_testing.utils.assertCompleteAndDispose
import me.tatocaster.covidstats.di.CovidStatsModule
import me.tatocaster.covidstats.di.DaggerCovidStatsUnitTestComponent
import org.junit.Test
import javax.inject.Inject

class CovidStatsInteractorImplTest : BaseUnitTest() {
    @Inject
    lateinit var covidStatsInteractorImpl: CovidStatsInteractor

    override fun beforeEachTest() {
        DaggerCovidStatsUnitTestComponent.factory()
            .create(
                CovidStatsModule,
                UnitTestApplication.testApplicationComponent
            )
            .inject(this)
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