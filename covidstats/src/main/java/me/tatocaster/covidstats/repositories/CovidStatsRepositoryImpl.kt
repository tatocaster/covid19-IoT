package me.tatocaster.covidstats.repositories

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import me.tatocaster.core.entities.CovidCase
import me.tatocaster.covidstats.data_sources.CovidStatsDataSource
import me.tatocaster.core.source.local.covidstats.CovidStatDbEntity
import me.tatocaster.core.source.remote.schema.CovidCaseCountryResponse
import me.tatocaster.covidstats.repositories.CovidStatsRepository
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
                item
            }
            .flatMap {
                updateCache(it)
                    .doOnError { th -> Timber.e(th, "error during cache update") }
                    .subscribe()
                Flowable.just(it)
            }
            .onErrorResumeNext(getLocal().toFlowable())
            .switchIfEmpty {
                getLocal().toFlowable()
            }
            .singleOrError()
    }

    override fun updateCache(covidCase: CovidCase): Completable =
        localDataSource.update(mapToEntity(covidCase))

    private fun getLocal(): Maybe<CovidCase> {
        return localDataSource
            .getStats()
            .map { item ->
                Timber.d("item %s", item)
                mapToCaseFromDb(item)
            }
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