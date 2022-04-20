package com.example.data.network.entities.forecastnetworkresponse.subclasses

import com.example.data.network.entities.currentweathernetworkresponse.subclasses.MainWeatherNetwork
import com.example.data.network.entities.currentweathernetworkresponse.subclasses.WeatherNetwork

data class ForecastNetwork(
    var dt: Long? = null,
    var main: MainWeatherNetwork? = null,
    var weather: List<WeatherNetwork>? = null,
    var dt_txt: String? = null,
)