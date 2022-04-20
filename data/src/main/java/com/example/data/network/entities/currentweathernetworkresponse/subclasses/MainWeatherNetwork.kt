package com.example.data.network.entities.currentweathernetworkresponse.subclasses

data class MainWeatherNetwork(
    var temp: Double? = null,
    var feels_like: Double? = null,
    var temp_min: Double? = null,
    var temp_max: Double? = null,
    var pressure: Long? = null,
    var humidity: Int? = null,
)