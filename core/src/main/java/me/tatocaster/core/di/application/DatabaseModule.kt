package me.tatocaster.core.di.application

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.tatocaster.core.di.qualifierscopes.ApplicationContext
import me.tatocaster.core.source.local.AppDatabase
import me.tatocaster.core.source.local.covidstats.CovidStatsDao

@Module
object DatabaseModule {

    @Reusable
    @Provides
    @JvmStatic
    fun appDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @JvmStatic
    fun covidStatsDao(database: AppDatabase): CovidStatsDao {
        return database.covidStatsDao()
    }
}