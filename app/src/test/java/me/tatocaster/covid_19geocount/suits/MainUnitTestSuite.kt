package me.tatocaster.covid_19geocount.suits

import me.tatocaster.covid_19geocount.features.covid_stats.data_sources.CovidStatsLocalDataSourceTest
import me.tatocaster.covid_19geocount.features.covid_stats.data_sources.CovidStatsRemoteDataSourceTest
import me.tatocaster.covid_19geocount.features.covid_stats.interactors.CovidStatsInteractorImplTest
import me.tatocaster.covid_19geocount.features.covid_stats.repositories.CovidStatsRepositoryImplTest
import me.tatocaster.covid_19geocount.features.covid_stats.screens.CovidStatsViewModelTest
import me.tatocaster.covid_19geocount.source.local.DatabaseTest
import me.tatocaster.covid_19geocount.source.remote.RemoteApiTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    RemoteApiTest::class,
    DatabaseTest::class,
    CovidStatsLocalDataSourceTest::class,
    CovidStatsRemoteDataSourceTest::class,
    CovidStatsRepositoryImplTest::class,
    CovidStatsInteractorImplTest::class,
    CovidStatsViewModelTest::class
)
class MainUnitTestSuite