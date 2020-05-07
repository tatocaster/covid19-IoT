package me.tatocaster.covidstats.data_sources

import io.reactivex.Maybe
import me.tatocaster.core.source.BaseSchedulerProvider
import me.tatocaster.core.source.local.covidstats.CovidStatDbEntity
import me.tatocaster.core.source.local.covidstats.CovidStatsDao

class CovidStatsLocalDataSource(
    private val dao: CovidStatsDao,
    private val scheduler: BaseSchedulerProvider
) :
    CovidStatsDataSource.Local {

    override fun getStats(): Maybe<CovidStatDbEntity> =
        dao.getStats()
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())

    override fun update(covidStat: CovidStatDbEntity) =
        dao.insertOrUpdate(covidStat)
            .subscribeOn(scheduler.io())
            .observeOn(scheduler.ui())

}