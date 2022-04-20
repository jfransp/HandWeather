package com.example.domain.usecases

import com.example.domain.model.Coordinates
import com.example.domain.repositories.CurrentWeatherRepository

class DoFetchCurrentWeatherUseCase(
    private val currentWeatherRepository: CurrentWeatherRepository
) {

    suspend operator fun invoke() {
        currentWeatherRepository.fetchCurrentLocationWeather()
    }

}