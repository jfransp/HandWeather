package com.example.domain.repositories

import com.example.domain.model.Coordinates
import com.example.domain.model.currentweather.CurrentWeather
import com.example.domain.model.forecast.Forecast
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {

    suspend fun getForecastByCoordinates(coordinates: Coordinates): List<Forecast>?

    suspend fun fetchCurrentLocationForecast()

    suspend fun getCurrentLocationForecast(): Flow<List<Forecast>>

}