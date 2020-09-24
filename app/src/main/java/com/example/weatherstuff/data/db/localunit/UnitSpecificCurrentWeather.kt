package com.example.weatherstuff.data.db.localunit

interface UnitSpecificCurrentWeather {
    val temperature: Double
    val windSpeed: Double
    val windDirection: String
    val precipitationVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
    val conditionText: String
    val conditionIconURL: String
}