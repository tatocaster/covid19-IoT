package me.tatocaster.core.source.local.covidstats

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe
import me.tatocaster.core.source.local.covidstats.CovidStatDbEntity


@Dao
interface CovidStatsDao {
    @Query("SELECT * FROM covid_stats LIMIT 1")
    fun getStats(): Maybe<CovidStatDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(covidCase: CovidStatDbEntity): Completable
}