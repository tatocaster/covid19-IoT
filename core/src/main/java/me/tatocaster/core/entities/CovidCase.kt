package me.tatocaster.core.entities

data class CovidCase(
    val country: String,
    val totalConfirmed: Int,
    val totalDeaths: Int,
    val totalRecovered: Int
)