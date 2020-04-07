package me.tatocaster.covid_19geocount.common.di.application

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import me.tatocaster.covid_19geocount.source.local.AppDatabase
import me.tatocaster.covid_19geocount.source.local.covidstats.CovidStatsDao
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun appDatabase(@AppContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun covidStatsDao(database: AppDatabase): CovidStatsDao {
        return database.covidStatsDao()
    }
}