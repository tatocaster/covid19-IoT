package me.tatocaster.covid_19geocount.common.di.application

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import me.tatocaster.covid_19geocount.features.covid_stats.data_sources.CovidStatsRemoteDataSource
import me.tatocaster.covid_19geocount.source.remote.RetrofitService
import javax.inject.Named

@Module
class ApplicationModule(private val app: Application) {

    @Provides
    @AppContext
    fun context(): Context = app.applicationContext

    @Provides
    fun resources(context: Context): Resources = context.resources
}