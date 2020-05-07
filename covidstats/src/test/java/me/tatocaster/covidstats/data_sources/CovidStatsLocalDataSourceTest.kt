package me.tatocaster.covidstats.data_sources

import me.tatocaster.core.source.local.covidstats.CovidStatDbEntity
import me.tatocaster.core_testing.UnitTestApplication
import me.tatocaster.core_testing.base.BaseUnitTest
import me.tatocaster.core_testing.utils.assertCompleteAndDispose
import me.tatocaster.covidstats.di.CovidStatsModule
import me.tatocaster.covidstats.di.DaggerCovidStatsUnitTestComponent
import org.junit.Test
import javax.inject.Inject


class CovidStatsLocalDataSourceTest : BaseUnitTest() {
    @Inject
    lateinit var localDataSource: CovidStatsDataSource.Local

    override fun beforeEachTest() {
        DaggerCovidStatsUnitTestComponent.factory()
            .create(
                CovidStatsModule,
                UnitTestApplication.testApplicationComponent
            )
            .inject(this)
    }

    @Test
    fun getStats_EmptyData_Success() {
        localDataSource.getStats()
            .map { it.country.toUpperCase() }
            .test()
            .assertNoValues()
            .assertCompleteAndDispose()
    }

    @Test
    fun update_CorrectData_Success() {
        val covidStatDbEntity = CovidStatDbEntity(
            "GEO", 190, 2, 40
        )
        localDataSource.update(covidStatDbEntity)
            .test()
            .assertCompleteAndDispose()

        localDataSource.getStats()
            .map { it.country.toUpperCase() }
            .test()
            .assertValue("GEO")
            .assertCompleteAndDispose()
    }
}