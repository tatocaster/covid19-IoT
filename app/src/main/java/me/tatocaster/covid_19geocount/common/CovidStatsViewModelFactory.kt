package me.tatocaster.covid_19geocount.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

class CovidStatsViewModelFactory(private val providerMap: Map<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providerMap[modelClass]?.get() as T
    }

}
