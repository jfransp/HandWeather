package com.example.data.persistance.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_city_weather")
data class CityCurrentWeatherLocal(
    @PrimaryKey
    val cityName: String,
    val mainState: String? = null,
    val description: String? = null,
    val temperature: Double? = null,
    val feelsLike: Double? = null,
    val maxTemp: Double? = null,
    val minTemp: Double? = null,
    val humidity: Int? = null,
    val visibility: Long? = null,
    val windSpeed: Double? = null
)