package me.tatocaster.core_testing.source.local

import me.tatocaster.core_testing.base.BaseUnitTest
import me.tatocaster.core.source.local.covidstats.CovidStatDbEntity
import me.tatocaster.core.source.local.covidstats.CovidStatsDao
import me.tatocaster.core_testing.UnitTestApplication
import me.tatocaster.core_testing.utils.assertCompleteAndDispose
import org.junit.Test
import javax.inject.Inject

class DatabaseTest : BaseUnitTest() {
    private val covidStatDbEntity = CovidStatDbEntity(
        "GEO", 190, 2, 40
    )

    @Inject
    lateinit var dao: CovidStatsDao

    override fun beforeEachTest() {
        UnitTestApplication.testApplicationComponent.inject(this)
    }

    @Test
    fun insertOrUpdate_CorrectData_Success() {
        dao.insertOrUpdate(covidStatDbEntity)
            .test()
            .assertCompleteAndDispose()
    }

/*    @Test
    fun getStats_Empty_Success() {
        dao
            .getStats()
            .count()
            .test()
            .assertValue(0)
            .assertCompleteAndDispose()
    }*/

    @Test
    fun getStats_InsertFirstWithData_Success() {
        dao
            .insertOrUpdate(covidStatDbEntity)
            .doOnSubscribe {
                dao
                    .getStats()
                    .map {
                        it.country
                    }
                    .test()
                    .assertValue("GEO")
                    .assertCompleteAndDispose()
            }
            .test()
    }

}