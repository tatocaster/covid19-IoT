package me.tatocaster.covidstats.screens

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.tatocaster.covidstats.interactors.CovidStatsInteractor
import javax.inject.Provider

class CovidStatsViewModelFactory(
    private val appContextProvider: Provider<Context>,
    private val covidStatsInteractorProvider: Provider<CovidStatsInteractor>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass != CovidStatsViewModel::class.java)
            throw IllegalArgumentException("Unknown ViewModel class")

        return CovidStatsViewModel(
            appContextProvider.get(),
            covidStatsInteractorProvider.get()
        ) as T
    }
}