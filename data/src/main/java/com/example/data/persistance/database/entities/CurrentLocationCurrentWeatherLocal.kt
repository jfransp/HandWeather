package com.example.data.persistance.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.util.Constants

@Entity(tableName = "cached_current_location_weather")
data class CurrentLocationCurrentWeatherLocal(
    @PrimaryKey
    var id: String = Constants.CACHED_SINGLE_ITEM_ID,
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