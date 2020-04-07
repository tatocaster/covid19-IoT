package me.tatocaster.covid_19geocount.source.remote.schema


import com.google.gson.annotations.SerializedName

data class CovidCaseCountryResponse(
    @SerializedName("country") val country: String,
    @SerializedName("date") val date: String,
    @SerializedName("active_today") val newConfirmed: Int,
    @SerializedName("died_today") val newDeaths: Int,
    @SerializedName("recovered_today") val newRecovered: Int,
    @SerializedName("all") val totalConfirmed: Int,
    @SerializedName("active") val totalActive: Int,
    @SerializedName("died") val totalDeaths: Int,
    @SerializedName("recovered") val totalRecovered: Int
)