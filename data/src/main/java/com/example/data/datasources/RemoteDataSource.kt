package com.example.data.datasources

import com.example.data.network.entities.currentweathernetworkresponse.CurrentWeatherNetworkResponse
import com.example.data.network.entities.currentweathernetworkresponse.subclasses.CoordinatesNetwork
import com.example.data.network.entities.forecastnetworkresponse.ForecastNetworkResponse
import com.example.data.network.services.ForecastApiClient
import com.example.data.network.services.CurrentWeatherApiClient

class RemoteDataSource(
    private val currentWeatherApiClient: CurrentWeatherApiClient,
    private val forecastApiClient: ForecastApiClient
) {

    suspend fun fetchLocationWeatherByCoordinates(coordinates: CoordinatesNetwork): CurrentWeatherNetworkResponse {
        return currentWeatherApiClient.getCurrentWeatherByCoordinates(coordinates = coordinates)
    }

    suspend fun fetchCityWeatherByCityName(
        cityName: String,
        countryId: String?
    ): CurrentWeatherNetworkResponse {
        return currentWeatherApiClient.getCurrentWeatherByCityName(city = cityName, countryId = countryId)
    }

    suspend fun fetchForecastByCoordinates(coordinates: CoordinatesNetwork): ForecastNetworkResponse {
        return forecastApiClient.getCurrentLocationForecastByCoordinates(coordinates = coordinates)
    }

    suspend fun fetchForecastByCityName(
        cityName: String,
        countryId: String?
    ): ForecastNetworkResponse {
        return forecastApiClient.getForecastByCityName(city = cityName, countryId = countryId)
    }

}