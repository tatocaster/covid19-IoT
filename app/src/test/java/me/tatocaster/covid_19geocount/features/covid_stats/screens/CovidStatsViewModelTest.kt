package me.tatocaster.covid_19geocount.features.covid_stats.screens

import io.reactivex.Single
import me.tatocaster.covid_19geocount.CovidStatsApplication
import me.tatocaster.covid_19geocount.UnitTestApplication
import me.tatocaster.covid_19geocount.base.JustError
import me.tatocaster.covid_19geocount.common.BaseUnitTest
import me.tatocaster.covid_19geocount.features.covid_stats.interactors.CovidStatsInteractor
import me.tatocaster.covid_19geocount.utils.getOrAwaitValue
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
        UnitTestApplication.testApplicationComponent.inject(this)

        // for work manager init. might be somewhere else as a provider :think:
        CovidStatsApplication.context = UnitTestApplication.context

        viewModel = CovidStatsViewModel(interactor)
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
        viewModel = CovidStatsViewModel(mockInteractor)
        `when`(mockInteractor.getStatsForGeorgia())
            .thenReturn(Single.error(Exception()))
        viewModel.getCovidCases()
        val state =
            viewModel.state.getOrAwaitValue() as JustError
        assertThat(state, isA(JustError::class.java))
    }
}
