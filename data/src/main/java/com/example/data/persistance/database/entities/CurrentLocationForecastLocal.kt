package com.example.data.persistance.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_current_location_forecast")
data class CurrentLocationForecastLocal(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var cityName: String? = null,
    var countryCode: String? = null,
    var dt: Long? = null,
    var dt_txt: String? = null,
    var temp: Double? = null,
    var feelsLike: Double? = null,
    var tempMin: Double? = null,
    var tempMax: Double? = null,
    var humidity: Int? = null,
    var main: String? = null,
    var description: String? = null
)