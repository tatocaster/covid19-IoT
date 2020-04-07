package me.tatocaster.covid_19geocount.common

import me.tatocaster.covid_19geocount.CovidStatsRobolectricUnitTestRunner
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(CovidStatsRobolectricUnitTestRunner::class)
abstract class BaseUnitTest {

    @Before
    abstract fun beforeEachTest()
}