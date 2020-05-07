package me.tatocaster.covidstats.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.tatocaster.core.di.qualifierscopes.ApplicationContext
import me.tatocaster.core.source.BaseSchedulerProvider
import me.tatocaster.core.source.local.covidstats.CovidStatsDao
import me.tatocaster.core.source.remote.RetrofitService
import me.tatocaster.covidstats.data_sources.CovidStatsDataSource
import me.tatocaster.covidstats.data_sources.CovidStatsLocalDataSource
import me.tatocaster.covidstats.data_sources.CovidStatsRemoteDataSource
import me.tatocaster.covidstats.interactors.CovidStatsInteractor
import me.tatocaster.covidstats.interactors.CovidStatsInteractorImpl
import me.tatocaster.covidstats.repositories.CovidStatsRepository
import me.tatocaster.covidstats.repositories.CovidStatsRepositoryImpl
import me.tatocaster.covidstats.screens.CovidStatsViewModelFactory
import javax.inject.Provider

@Module
object CovidStatsModule {
    @Provides
    @Reusable
    fun covidStatsRemoteDataSource(retrofitService: RetrofitService): CovidStatsDataSource.Remote =
        CovidStatsRemoteDataSource(retrofitService)

    @Provides
    @Reusable
    fun covidStatsLocalDataSource(
        dao: CovidStatsDao,
        scheduler: BaseSchedulerProvider
    ): CovidStatsDataSource.Local =
        CovidStatsLocalDataSource(dao, scheduler)

    @Provides
    @Reusable
    fun covidStatsRepository(
        remoteDataSource: CovidStatsDataSource.Remote,
        localDataSource: CovidStatsDataSource.Local
    ): CovidStatsRepository =
        CovidStatsRepositoryImpl(remoteDataSource, localDataSource)

    @Provides
    @Reusable
    fun covidStatsInteractor(repository: CovidStatsRepository): CovidStatsInteractor =
        CovidStatsInteractorImpl(repository)

    @Provides
    @Reusable
    fun covidStatsViewModelFactory(
        @ApplicationContext appContextProvider: Provider<Context>,
        covidStatsInteractorProvider: Provider<CovidStatsInteractor>
    ): CovidStatsViewModelFactory = CovidStatsViewModelFactory(appContextProvider, covidStatsInteractorProvider)

}