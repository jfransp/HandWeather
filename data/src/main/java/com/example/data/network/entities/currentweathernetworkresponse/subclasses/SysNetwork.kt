package com.example.data.network.entities.currentweathernetworkresponse.subclasses

data class SysNetwork(
    var type: Long? = null,
    var id: Long? = null,
    var message: Double? = null,
    var country: String? = null,
    var sunrise: Long? = null,
    var sunset: Long? = null
)