package me.tatocaster.core.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import me.tatocaster.core.source.local.covidstats.CovidStatDbEntity
import me.tatocaster.core.source.local.covidstats.CovidStatsDao

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