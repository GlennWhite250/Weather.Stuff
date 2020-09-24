package com.example.weatherstuff.data.resposne

import com.google.gson.annotations.SerializedName


data class CurrentWeatherResposne(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location,
    val request: Request
)