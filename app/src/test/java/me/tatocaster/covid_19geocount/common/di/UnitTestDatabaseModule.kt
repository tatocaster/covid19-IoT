package me.tatocaster.covid_19geocount.common.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dagger.Module
import dagger.Provides
import me.tatocaster.covid_19geocount.source.local.AppDatabase
import me.tatocaster.covid_19geocount.source.local.covidstats.CovidStatsDao
import javax.inject.Singleton

@Module
object UnitTestDatabaseModule {

    @Singleton
    @Provides
    fun appDatabase(): AppDatabase {
        val context = ApplicationProvider.getApplicationContext<Context>()
        return Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun covidStatsDao(database: AppDatabase): CovidStatsDao {
        return database.covidStatsDao()
    }
}
