package com.example.domain.repositories

import com.example.domain.model.Coordinates
import com.example.domain.model.currentweather.CurrentWeather
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepository {
    suspend fun getCurrentWeatherByCoordinates(coordinates: Coordinates): CurrentWeather

    suspend fun getCurrentWeatherByCityNameAndCountryId(
        cityName: String,
        countryId: String? = null
    ): CurrentWeather

    suspend fun fetchCurrentLocationWeather()

    suspend fun getCurrentLocationWeather(): Flow<CurrentWeather>

    suspend fun fetchCityWeather(cityName: String, countryId: String)

    suspend fun getCityWeather(cityName: String, countryId: String? = null): Flow<CurrentWeather>
}