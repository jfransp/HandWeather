package com.example.data.network.services

import com.example.data.network.AppClient
import com.example.data.network.entities.currentweathernetworkresponse.subclasses.CoordinatesNetwork
import com.example.data.network.entities.forecastnetworkresponse.ForecastNetworkResponse
import com.example.data.util.Constants
import io.ktor.client.request.*

class ForecastApiClient(
    val client: AppClient
) {

    //Gets forecast data for a given latitude and longitude
    suspend fun getCurrentLocationForecastByCoordinates(
        coordinates: CoordinatesNetwork
    ) : ForecastNetworkResponse {
        return client().get("${Constants.BASE_URL}/${Constants.FORECAST_PATH}") {
            parameter("lat", coordinates.lat)
            parameter("lon", coordinates.lon)
            parameter("units", Constants.UNIT_OF_MEASUREMENT)
            parameter("appid", client.getApiKey())
        }
    }

    //Gets forecast data for a given location by city name and country id
    suspend fun getForecastByCityName(
        city: String,
        countryId: String? = null
    ) : ForecastNetworkResponse {
        return client().get("${Constants.BASE_URL}/${Constants.FORECAST_PATH}") {
            val q = if (countryId != null)
                "$city,$countryId" else
                city
            parameter("q", q)
            parameter("units", Constants.UNIT_OF_MEASUREMENT)
            parameter("appid", client.getApiKey())
        }
    }

}
