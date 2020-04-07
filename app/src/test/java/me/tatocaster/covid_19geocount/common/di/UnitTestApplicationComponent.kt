package me.tatocaster.covid_19geocount.common.di

import dagger.Component
import me.tatocaster.covid_19geocount.common.di.application.ApplicationAssistedInjectModule
import me.tatocaster.covid_19geocount.common.di.application.ApplicationModule
import me.tatocaster.covid_19geocount.common.di.application.WorkerModule
import me.tatocaster.covid_19geocount.features.covid_stats.CovidStatsModule
import me.tatocaster.covid_19geocount.features.covid_stats.data_sources.CovidStatsLocalDataSourceTest
import me.tatocaster.covid_19geocount.features.covid_stats.data_sources.CovidStatsRemoteDataSourceTest
import me.tatocaster.covid_19geocount.features.covid_stats.interactors.CovidStatsInteractorImplTest
import me.tatocaster.covid_19geocount.features.covid_stats.repositories.CovidStatsRepositoryImplTest
import me.tatocaster.covid_19geocount.features.covid_stats.screens.CovidStatsViewModelTest
import me.tatocaster.covid_19geocount.source.local.DatabaseTest
import me.tatocaster.covid_19geocount.source.remote.RemoteApiTest
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ApplicationAssistedInjectModule::class,
        UnitTestNetworkModule::class,
        CovidStatsModule::class,
        UnitTestDatabaseModule::class
    ]
)
interface UnitTestApplicationComponent {
    fun inject(remoteApiTest: RemoteApiTest)
    fun inject(databaseTest: DatabaseTest)
    fun inject(localDataSourceTest: CovidStatsLocalDataSourceTest)
    fun inject(remoteDataSourceTest: CovidStatsRemoteDataSourceTest)
    fun inject(covidStatsRepositoryImplTest: CovidStatsRepositoryImplTest)
    fun inject(covidStatsInteractorImplTest: CovidStatsInteractorImplTest)
    fun inject(covidStatsViewModelTest: CovidStatsViewModelTest)
}