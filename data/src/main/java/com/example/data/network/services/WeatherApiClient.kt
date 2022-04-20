package com.example.data.network.services

import android.util.Log
import com.example.data.network.AppClient
import com.example.data.network.entities.currentweathernetworkresponse.CurrentWeatherNetworkResponse
import com.example.data.network.entities.currentweathernetworkresponse.subclasses.CoordinatesNetwork
import com.example.data.util.Constants.Companion.BASE_URL
import com.example.data.util.Constants.Companion.UNIT_OF_MEASUREMENT
import com.example.data.util.Constants.Companion.WEATHER_PATH
import io.ktor.client.request.*

class WeatherApiClient(
    private val client: AppClient
) {

    //Gets current weather data for a given location by latitude and longitude
    suspend fun getCurrentWeatherByCoordinates(
        coordinates: CoordinatesNetwork
    ) : CurrentWeatherNetworkResponse {
        Log.v("weatherApiClient", "getCurrentWeatherByCoordinates Called")
        return client().get("$BASE_URL/$WEATHER_PATH") {
            parameter("lat", coordinates.lat)
            parameter("lon", coordinates.lon)
            parameter("units", UNIT_OF_MEASUREMENT)
            parameter("appid", client.getApiKey())
        }
    }

    //Gets current weather data for a given location by city name and country id
    suspend fun getCurrentWeatherByCityName(
        city: String,
        countryId: String? = null
    ) : CurrentWeatherNetworkResponse {
        Log.v("weatherApiClient", "getCurrentWeatherByCityName Called")
        return client().get("$BASE_URL/$WEATHER_PATH") {
            val q = if (countryId != null)
                "$city,$countryId" else
                city
            parameter("q", q)
            parameter("units", UNIT_OF_MEASUREMENT)
            parameter("appid", client.getApiKey())
        }
    }

}
