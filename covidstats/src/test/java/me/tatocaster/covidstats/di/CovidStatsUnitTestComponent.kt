package me.tatocaster.covidstats.di

import dagger.Component
import me.tatocaster.core.di.application.CoreComponent
import me.tatocaster.covidstats.data_sources.CovidStatsLocalDataSourceTest
import me.tatocaster.covidstats.data_sources.CovidStatsRemoteDataSourceTest
import me.tatocaster.covidstats.interactors.CovidStatsInteractorImplTest
import me.tatocaster.covidstats.repositories.CovidStatsRepositoryImplTest
import me.tatocaster.covidstats.screens.CovidStatsViewModelTest

@CovidStatsFeatureScope
@Component(
    dependencies = [
        CoreComponent::class
    ],
    modules = [
        CovidStatsModule::class
    ]
)
interface CovidStatsUnitTestComponent {
    @Component.Factory
    interface Factory {
        fun create(
            covidStatsModule: CovidStatsModule,
            coreComponent: CoreComponent
        ): CovidStatsUnitTestComponent
    }

    fun inject(localDataSourceTest: CovidStatsLocalDataSourceTest)
    fun inject(remoteDataSourceTest: CovidStatsRemoteDataSourceTest)
    fun inject(covidStatsRepositoryImplTest: CovidStatsRepositoryImplTest)
    fun inject(covidStatsInteractorImplTest: CovidStatsInteractorImplTest)
    fun inject(covidStatsViewModelTest: CovidStatsViewModelTest)
}