package com.example.data.datasources

import com.example.data.network.entities.currentweathernetworkresponse.CurrentWeatherNetworkResponse
import com.example.data.network.entities.currentweathernetworkresponse.subclasses.CoordinatesNetwork
import com.example.data.network.entities.forecastnetworkresponse.ForecastNetworkResponse
import com.example.data.network.services.ForecastApiClient
import com.example.data.network.services.WeatherApiClient

class RemoteDataSource(
    private val weatherApiClient: WeatherApiClient,
    private val forecastApiClient: ForecastApiClient
) {

    suspend fun fetchLocationWeatherByCoordinates(coordinates: CoordinatesNetwork): CurrentWeatherNetworkResponse {
        return weatherApiClient.getCurrentWeatherByCoordinates(coordinates = coordinates)
    }

    suspend fun fetchCityWeatherByCityName(
        cityName: String,
        countryId: String?
    ): CurrentWeatherNetworkResponse {
        return weatherApiClient.getCurrentWeatherByCityName(city = cityName, countryId = countryId)
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