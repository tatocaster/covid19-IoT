package me.tatocaster.core_testing.source.remote

import me.tatocaster.core.source.getResponseBody
import me.tatocaster.core.source.remote.RetrofitService
import me.tatocaster.core_testing.UnitTestApplication
import me.tatocaster.core_testing.base.BaseUnitTest
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class RemoteApiTest : BaseUnitTest() {

    @Inject
    lateinit var apiService: RetrofitService


    @Before
    override fun beforeEachTest() {
        println("before test")
        UnitTestApplication.testApplicationComponent.inject(this)
    }


    @Test
    // just to execute @Test function
    fun getCovidStats_Success() {
        apiService.getCovidStats()
            .getResponseBody()
            .subscribe(
                { println("success ${it.country}") },
                { println("error") })
    }
}