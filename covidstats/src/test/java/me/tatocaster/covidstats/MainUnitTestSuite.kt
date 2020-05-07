package me.tatocaster.covidstats

import me.tatocaster.covidstats.data_sources.CovidStatsLocalDataSourceTest
import me.tatocaster.covidstats.data_sources.CovidStatsRemoteDataSourceTest
import me.tatocaster.covidstats.interactors.CovidStatsInteractorImplTest
import me.tatocaster.covidstats.repositories.CovidStatsRepositoryImplTest
import me.tatocaster.covidstats.screens.CovidStatsViewModelTest
import me.tatocaster.core_testing.source.local.DatabaseTest
import me.tatocaster.core_testing.source.remote.RemoteApiTest
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