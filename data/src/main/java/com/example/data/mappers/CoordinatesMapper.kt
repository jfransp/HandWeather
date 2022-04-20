package com.example.data.mappers

import com.example.data.network.entities.currentweathernetworkresponse.subclasses.CoordinatesNetwork
import com.example.domain.model.Coordinates

class CoordinatesMapper {

    fun fromModelToNetwork(input: Coordinates): CoordinatesNetwork {
        return CoordinatesNetwork(
            lat = input.lat,
            lon = input.lon
        )
    }

    companion object {
        fun provide(): CoordinatesMapper {
            return CoordinatesMapper()
        }
    }

}