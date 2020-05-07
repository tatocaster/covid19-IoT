package me.tatocaster.core.source.local.covidstats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "covid_stats")
data class CovidStatDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "total_confirmed")
    val totalConfirmed: Int,

    @ColumnInfo(name = "total_death")
    val totalDeaths: Int,

    @ColumnInfo(name = "total_recovered")
    val totalRecovered: Int
)