package me.tatocaster.covid_19geocount.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.tatocaster.covid_19geocount.common.entities.CovidCase
import me.tatocaster.covid_19geocount.source.local.AppDatabase
import me.tatocaster.covid_19geocount.source.local.covidstats.CovidStatDbEntity
import me.tatocaster.covid_19geocount.source.local.covidstats.CovidStatsDao

@Database(
    entities = [
        CovidStatDbEntity::class
    ],
    version = AppDatabase.VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun covidStatsDao(): CovidStatsDao

    companion object {
        const val VERSION = 1
        const val DATABASE_NAME = "covidstats.db"
    }
}