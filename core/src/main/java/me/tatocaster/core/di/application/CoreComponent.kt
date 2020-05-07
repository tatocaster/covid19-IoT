package me.tatocaster.core.di.application

import me.tatocaster.core.source.BaseSchedulerProvider
import me.tatocaster.core.source.local.covidstats.CovidStatsDao
import me.tatocaster.core.source.remote.RetrofitService


interface CoreComponent {
    val retrofit: RetrofitService
    val covidStatsDao: CovidStatsDao
    val rsSchedulers: BaseSchedulerProvider
}