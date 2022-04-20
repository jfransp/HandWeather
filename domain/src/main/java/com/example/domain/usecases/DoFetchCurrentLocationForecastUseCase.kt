package com.example.domain.usecases

import com.example.domain.repositories.ForecastRepository

class DoFetchCurrentLocationForecastUseCase(
    private val forecastRepository: ForecastRepository
) {

    suspend operator fun invoke() {
        forecastRepository.fetchCurrentLocationForecast()
    }

}