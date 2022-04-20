package com.example.data.network.entities.currentweathernetworkresponse

import com.example.data.network.entities.currentweathernetworkresponse.subclasses.*

data class CurrentWeatherNetworkResponse(
    var coord: CoordinatesNetwork? = null,
    var weather: List<WeatherNetwork>? = null,
    var base: String? = null,
    var main: MainWeatherNetwork? = null,
    var visibility: Long? = null,
    var wind: WindNetwork? = null,
    var clouds: CloudsNetwork? = null,
    var dt: Long? = null,
    var sys: SysNetwork? = null,
    var timezoe: Long? = null,
    var id: Long? = null,
    var name: String? = null,
    var cod: Long? = null
)