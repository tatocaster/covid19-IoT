package me.tatocaster.covid_19geocount.source.remote

import me.tatocaster.covid_19geocount.UnitTestApplication
import me.tatocaster.covid_19geocount.common.BaseUnitTest
import me.tatocaster.covid_19geocount.source.getResponseBody
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