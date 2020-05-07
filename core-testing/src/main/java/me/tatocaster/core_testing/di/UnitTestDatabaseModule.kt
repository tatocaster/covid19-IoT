package me.tatocaster.core_testing.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.tatocaster.core.source.local.AppDatabase
import me.tatocaster.core.source.local.covidstats.CovidStatsDao

@Module
object UnitTestDatabaseModule {

    @Reusable
    @Provides
    @JvmStatic
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
