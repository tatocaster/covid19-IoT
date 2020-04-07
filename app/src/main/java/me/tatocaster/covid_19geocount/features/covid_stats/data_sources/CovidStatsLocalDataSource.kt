package me.tatocaster.covid_19geocount.features.covid_stats.data_sources

import io.reactivex.Maybe
import me.tatocaster.covid_19geocount.source.applySchedulers
import me.tatocaster.covid_19geocount.source.local.covidstats.CovidStatDbEntity
import me.tatocaster.covid_19geocount.source.local.covidstats.CovidStatsDao

class CovidStatsLocalDataSource(private val dao: CovidStatsDao) :
    CovidStatsDataSource.Local {

    override fun getStats(): Maybe<CovidStatDbEntity> =
        dao.getStats()
            .applySchedulers()

    override fun update(covidStat: CovidStatDbEntity) =
        dao.insertOrUpdate(covidStat)
            .applySchedulers()

}