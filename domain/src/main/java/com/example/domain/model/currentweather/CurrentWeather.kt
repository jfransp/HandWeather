package com.example.domain.model.currentweather

data class CurrentWeather(
    val mainState: String? = null,
    val description: String? = null,
    val temperature: Double? = null,
    val feelsLike: Double? = null,
    val maxTemp: Double? = null,
    val minTemp: Double? = null,
    val humidity: Int? = null,
    val visibility: Long? = null,
    val windSpeed: Double? = null,
    val cityName: String? = null
)