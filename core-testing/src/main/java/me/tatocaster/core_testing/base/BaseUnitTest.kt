package me.tatocaster.core_testing.base

import me.tatocaster.core_testing.CovidStatsRobolectricUnitTestRunner
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(CovidStatsRobolectricUnitTestRunner::class)
@Config(manifest = Config.NONE)
abstract class BaseUnitTest {

    @Before
    abstract fun beforeEachTest()
}