package me.tatocaster.covid_19geocount.common.entities

data class CovidCase(
    val country: String,
    val totalConfirmed: Int,
    val totalDeaths: Int,
    val totalRecovered: Int
)