package com.example.data.network.entities.forecastnetworkresponse.subclasses

import com.example.data.network.entities.currentweathernetworkresponse.subclasses.CoordinatesNetwork

data class CityNetwork(
    var id: Long? = null,
    var name: String? = null,
    var coord: CoordinatesNetwork? = null,
    var country: String? = null
)