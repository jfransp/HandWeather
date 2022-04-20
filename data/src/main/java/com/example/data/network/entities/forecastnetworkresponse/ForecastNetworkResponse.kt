package com.example.data.network.entities.forecastnetworkresponse

import com.example.data.network.entities.forecastnetworkresponse.subclasses.CityNetwork
import com.example.data.network.entities.forecastnetworkresponse.subclasses.ForecastNetwork

data class ForecastNetworkResponse(
    var cod: String? = null,
    var message: Long? = null,
    var cnt: Long? = null,
    var list: List<ForecastNetwork>? = null,
    var city: CityNetwork? = null
)