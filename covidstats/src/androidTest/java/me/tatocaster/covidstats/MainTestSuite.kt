package me.tatocaster.covidstats

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    CovidStatsTest::class,
    CovidStatsFlowTest::class
)
class MainTestSuite
