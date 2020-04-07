package me.tatocaster.covid_19geocount.features.covid_stats.data_sources

import me.tatocaster.covid_19geocount.CovidStatsRobolectricUnitTestRunner
import me.tatocaster.covid_19geocount.UnitTestApplication
import me.tatocaster.covid_19geocount.common.BaseUnitTest
import me.tatocaster.covid_19geocount.utils.assertCompleteAndDispose
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

class CovidStatsRemoteDataSourceTest : BaseUnitTest() {
    @Inject
    lateinit var remoteDataSource: CovidStatsDataSource.Remote

    override fun beforeEachTest() {
        UnitTestApplication.testApplicationComponent.inject(this)
    }

    @Test
    fun getStatsForCountries_Success() {
        remoteDataSource.getStatsForCountries()
            .map { it.country.toUpperCase() }
            .test()
            .assertValue("GEO")
            .assertCompleteAndDispose()
    }
}