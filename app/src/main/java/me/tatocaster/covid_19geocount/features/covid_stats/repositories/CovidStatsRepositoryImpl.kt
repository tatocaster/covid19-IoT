package me.tatocaster.covid_19geocount.features.covid_stats.repositories

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import me.tatocaster.covid_19geocount.common.entities.CovidCase
import me.tatocaster.covid_19geocount.features.covid_stats.data_sources.CovidStatsDataSource
import me.tatocaster.covid_19geocount.source.local.covidstats.CovidStatDbEntity
import me.tatocaster.covid_19geocount.source.remote.schema.CovidCaseCountryResponse
import timber.log.Timber

class CovidStatsRepositoryImpl(
    private val remoteDataSource: CovidStatsDataSource.Remote,
    private val localDataSource: CovidStatsDataSource.Local
) :
    CovidStatsRepository {

    override fun getStats(): Single<CovidCase> {
        return remoteDataSource
            .getStatsForCountries()
            .toFlowable()
            .map {
                val item = mapToCaseFromRemote(it)
                updateCache(item)
                item
            }
            .switchIfEmpty {
                getLocal().toFlowable()
            }
            .onErrorResumeNext(getLocal().toFlowable())
            .singleOrError()
        /*return remoteDataSource
            .getStatsForCountries()
            .toFlowable()
            .map {
                val item = mapToCaseFromRemote(it)
                updateCache(item)
                item
            }
            .publish {
                it.mergeWith(
                    getLocal().toFlowable().takeUntil(it)
                ).onErrorResumeNext(it)
            }
            .onErrorResumeNext(getLocal().toFlowable())
            .singleOrError()*/
    }

    override fun updateCache(covidCase: CovidCase): Completable =
        localDataSource.update(mapToEntity(covidCase))
            .doOnComplete {
                Timber.d("local updated")
            }
            .doOnError {
                Timber.e(it, "local updated")
            }

    private fun getLocal(): Maybe<CovidCase> {
        return localDataSource
            .getStats()
            .map { item -> mapToCaseFromDb(item) }
    }


    // TODO: probably we need mapper class layer
    private fun mapToEntity(covidStat: CovidCase) =
        CovidStatDbEntity(
            covidStat.country,
            covidStat.totalConfirmed,
            covidStat.totalDeaths,
            covidStat.totalRecovered
        )

    private fun mapToCaseFromDb(it: CovidStatDbEntity) =
        CovidCase(
            it.country,
            it.totalConfirmed,
            it.totalDeaths,
            it.totalRecovered
        )

    private fun mapToCaseFromRemote(it: CovidCaseCountryResponse) =
        CovidCase(
            it.country,
            it.totalConfirmed,
            it.totalDeaths,
            it.totalRecovered
        )

}