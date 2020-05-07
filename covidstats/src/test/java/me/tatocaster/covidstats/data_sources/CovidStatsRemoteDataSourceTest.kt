package me.tatocaster.covidstats.data_sources

import me.tatocaster.core_testing.UnitTestApplication
import me.tatocaster.core_testing.base.BaseUnitTest
import me.tatocaster.core_testing.utils.assertCompleteAndDispose
import me.tatocaster.covidstats.di.CovidStatsModule
import me.tatocaster.covidstats.di.DaggerCovidStatsUnitTestComponent
import org.junit.Test
import javax.inject.Inject

class CovidStatsRemoteDataSourceTest : BaseUnitTest() {
    @Inject
    lateinit var remoteDataSource: CovidStatsDataSource.Remote

    override fun beforeEachTest() {
        DaggerCovidStatsUnitTestComponent.factory()
            .create(
                CovidStatsModule,
                UnitTestApplication.testApplicationComponent
            )
            .inject(this)
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