package me.tatocaster.covidstats.screens

import android.content.Context
import io.reactivex.Single
import me.tatocaster.core.base.JustError
import me.tatocaster.core_testing.UnitTestApplication
import me.tatocaster.core_testing.base.BaseUnitTest
import me.tatocaster.core_testing.utils.getOrAwaitValue
import me.tatocaster.covidstats.di.CovidStatsModule
import me.tatocaster.covidstats.di.DaggerCovidStatsUnitTestComponent
import me.tatocaster.covidstats.interactors.CovidStatsInteractor
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.isA
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import javax.inject.Inject

class CovidStatsViewModelTest : BaseUnitTest() {
    lateinit var viewModel: CovidStatsViewModel

    @Inject
    lateinit var interactor: CovidStatsInteractor

    override fun beforeEachTest() {
        DaggerCovidStatsUnitTestComponent.factory()
            .create(
                CovidStatsModule,
                UnitTestApplication.testApplicationComponent
            )
            .inject(this)

        viewModel = CovidStatsViewModel(UnitTestApplication.context, interactor)
    }

    @Test
    fun getCovidCases_Success() {
        viewModel.getCovidCases()
        val state =
            viewModel.state.getOrAwaitValue() as CovidStatsViewModel.CasesLoaded
        assertThat(state.payload.country, `is`("GEO"))
    }

    @Test
    fun getCovidCases_Fail() {
        val mockInteractor = mock(CovidStatsInteractor::class.java)
        `when`(mockInteractor.getStatsForGeorgia())
            .thenReturn(Single.error(Exception()))
        viewModel = CovidStatsViewModel(UnitTestApplication.context, mockInteractor)
        viewModel.getCovidCases()
        val state =
            viewModel.state.getOrAwaitValue() as JustError
        assertThat(state, isA(JustError::class.java))
    }
}
