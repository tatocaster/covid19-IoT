package me.tatocaster.covid_19geocount.features.covid_stats.data_sources

import me.tatocaster.covid_19geocount.UnitTestApplication
import me.tatocaster.covid_19geocount.common.BaseUnitTest
import me.tatocaster.covid_19geocount.source.local.covidstats.CovidStatDbEntity
import me.tatocaster.covid_19geocount.utils.assertCompleteAndDispose
import org.junit.Test
import javax.inject.Inject


class CovidStatsLocalDataSourceTest : BaseUnitTest() {
    @Inject
    lateinit var localDataSource: CovidStatsDataSource.Local

    override fun beforeEachTest() {
        UnitTestApplication.testApplicationComponent.inject(this)
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
    }
}