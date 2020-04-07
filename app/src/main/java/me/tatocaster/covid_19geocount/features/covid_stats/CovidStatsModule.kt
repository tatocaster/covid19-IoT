package me.tatocaster.covid_19geocount.features.covid_stats

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import me.tatocaster.covid_19geocount.common.di.presentation.ViewModelModule
import me.tatocaster.covid_19geocount.features.covid_stats.data_sources.CovidStatsDataSource
import me.tatocaster.covid_19geocount.features.covid_stats.data_sources.CovidStatsLocalDataSource
import me.tatocaster.covid_19geocount.features.covid_stats.data_sources.CovidStatsRemoteDataSource
import me.tatocaster.covid_19geocount.features.covid_stats.interactors.CovidStatsInteractor
import me.tatocaster.covid_19geocount.features.covid_stats.interactors.CovidStatsInteractorImpl
import me.tatocaster.covid_19geocount.features.covid_stats.repositories.CovidStatsRepository
import me.tatocaster.covid_19geocount.features.covid_stats.repositories.CovidStatsRepositoryImpl
import me.tatocaster.covid_19geocount.features.covid_stats.screens.CovidStatsViewModel
import me.tatocaster.covid_19geocount.source.local.covidstats.CovidStatsDao
import me.tatocaster.covid_19geocount.source.remote.RetrofitService

@Module
class CovidStatsModule {
    @Provides
    fun covidStatsRemoteDataSource(retrofitService: RetrofitService): CovidStatsDataSource.Remote =
        CovidStatsRemoteDataSource(retrofitService)

    @Provides
    fun covidStatsLocalDataSource(dao: CovidStatsDao): CovidStatsDataSource.Local =
        CovidStatsLocalDataSource(dao)

    @Provides
    fun covidStatsRepository(
        remoteDataSource: CovidStatsDataSource.Remote,
        localDataSource: CovidStatsDataSource.Local
    ): CovidStatsRepository =
        CovidStatsRepositoryImpl(remoteDataSource, localDataSource)

    @Provides
    fun covidStatsInteractor(repository: CovidStatsRepository): CovidStatsInteractor =
        CovidStatsInteractorImpl(repository)

    @Provides
    @IntoMap
    @ViewModelModule.ViewModelKey(CovidStatsViewModel::class)
    fun covidStatsViewModel(covidStatsInteractor: CovidStatsInteractor): ViewModel =
        CovidStatsViewModel(covidStatsInteractor)

}