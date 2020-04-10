package me.tatocaster.covid_19geocount.features.covid_stats.repositories

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import me.tatocaster.covid_19geocount.UnitTestApplication
import me.tatocaster.covid_19geocount.common.BaseUnitTest
import me.tatocaster.covid_19geocount.common.entities.CovidCase
import me.tatocaster.covid_19geocount.features.covid_stats.data_sources.CovidStatsDataSource
import me.tatocaster.covid_19geocount.source.local.covidstats.CovidStatDbEntity
import me.tatocaster.covid_19geocount.source.remote.schema.CovidCaseCountryResponse
import me.tatocaster.covid_19geocount.utils.assertCompleteAndDispose
import me.tatocaster.covid_19geocount.utils.assertNotCompleteAndDispose
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import javax.inject.Inject

class CovidStatsRepositoryImplTest : BaseUnitTest() {

    private val covidStatDbEntity = CovidStatDbEntity(
        "GEO", 190, 2, 40
    )

    @Inject
    lateinit var repository: CovidStatsRepository

    @Inject
    lateinit var localDataSource: CovidStatsDataSource.Local

    @Inject
    lateinit var remoteDataSource: CovidStatsDataSource.Remote

    @Mock
    lateinit var mockLocalDataSource: CovidStatsDataSource.Local

    @Mock
    lateinit var mockRemoteDataSource: CovidStatsDataSource.Remote

    override fun beforeEachTest() {
        UnitTestApplication.testApplicationComponent.inject(this)
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getStats_Success() {
        repository.getStats()
            .map { it.country }
            .test()
            .assertValue("GEO")
            .assertCompleteAndDispose()
    }

    @Test
    fun getStats_BothSourcesError_Fail() {
        repository = CovidStatsRepositoryImpl(mockRemoteDataSource, mockLocalDataSource)

        `when`(mockLocalDataSource.getStats())
            .thenReturn(Maybe.error(Exception()))

        `when`(mockRemoteDataSource.getStatsForCountries())
            .thenReturn(Single.error(Exception()))

        repository.getStats()
            .test()
            .assertNotCompleteAndDispose()
    }

    @Test
    fun getStats_WithDbFail_Success() {
        repository = CovidStatsRepositoryImpl(remoteDataSource, mockLocalDataSource)

        `when`(mockLocalDataSource.getStats())
            .thenReturn(Maybe.error(Exception()))

        `when`(mockLocalDataSource.update(covidStatDbEntity))
            .thenReturn(Completable.error(Exception()))

        repository.getStats()
            .map { it.country }
            .test()
            .assertValue("GEO")
            .assertCompleteAndDispose()
    }

    @Test
    fun getStats_WithDbEmptyOnly_Success() {
        repository = CovidStatsRepositoryImpl(remoteDataSource, mockLocalDataSource)

        `when`(mockLocalDataSource.getStats())
            .thenReturn(Maybe.empty())

        `when`(mockLocalDataSource.update(covidStatDbEntity))
            .thenReturn(Completable.complete())

        repository.getStats()
            .map { it.country }
            .test()
            .assertValue("GEO")
            .assertCompleteAndDispose()
    }

    @Test
    fun getStats_WithDbEmptyAndNetworkError_Fail() {
        repository = CovidStatsRepositoryImpl(mockRemoteDataSource, mockLocalDataSource)

        `when`(mockLocalDataSource.getStats())
            .thenReturn(Maybe.empty())

        `when`(mockRemoteDataSource.getStatsForCountries())
            .thenReturn(Single.error(Exception()))

        repository.getStats()
            .test()
            .assertNotCompleteAndDispose()
    }

    @Test
    fun getStats_SuccessButUpdateFails_Success() {
        repository = CovidStatsRepositoryImpl(remoteDataSource, mockLocalDataSource)

        `when`(mockLocalDataSource.getStats())
            .thenReturn(Maybe.empty())

        `when`(mockLocalDataSource.update(covidStatDbEntity))
            .thenReturn(Completable.error(Exception()))

        repository.getStats()
            .map { it.country }
            .test()
            .assertValue("GEO")
            .assertCompleteAndDispose()
    }

    @Test
    fun getStats_SuccessNetworkAndCheckCacheIfUpdated_Success() {
        repository = CovidStatsRepositoryImpl(remoteDataSource, localDataSource)

        repository.getStats()
            .map { it.country }
            .test()
            .assertValue("GEO")
            .assertCompleteAndDispose()

        localDataSource
            .getStats()
            .map { it.totalConfirmed }
            .test()
            .assertValue(190)
            .assertCompleteAndDispose()
    }

    @Test
    fun getStats_WithNetworkFail_Success() {
        // let's insert first
        localDataSource.update(covidStatDbEntity).test().assertCompleteAndDispose()

        repository = CovidStatsRepositoryImpl(mockRemoteDataSource, localDataSource)

        `when`(mockRemoteDataSource.getStatsForCountries())
            .thenReturn(Single.error(Exception()))

        repository.getStats()
            .map {
                println(it.country)
                it.country
            }
            .test()
            .assertValue("GEO")
            .assertCompleteAndDispose()
    }

    @Test
    fun updateCache_NotNull_Success() {
        val covidCase = CovidCase("GEO", 180, 2, 140)
        repository.updateCache(covidCase)
            .test()
            .assertCompleteAndDispose()
    }
}