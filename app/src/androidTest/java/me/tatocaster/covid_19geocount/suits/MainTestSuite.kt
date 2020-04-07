package me.tatocaster.covid_19geocount.suits

import me.tatocaster.covid_19geocount.features.covid_stats.CovidStatsFlowTest
import me.tatocaster.covid_19geocount.features.covid_stats.CovidStatsTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    CovidStatsTest::class,
    CovidStatsFlowTest::class
)
class MainTestSuite