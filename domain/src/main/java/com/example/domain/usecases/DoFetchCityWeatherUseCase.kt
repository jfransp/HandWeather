package com.example.domain.usecases

import com.example.domain.repositories.CurrentWeatherRepository
import com.example.domain.util.Resource

class DoFetchCityWeatherUseCase(
    private val currentWeatherRepository: CurrentWeatherRepository,
    private val errorHandler: com.example.domain.error.ErrorHandler
) {

    suspend operator fun invoke(cityName: String, countryCode: String): Resource<Unit> {
        return try {
            Resource.Success(
                currentWeatherRepository.fetchCityWeather(cityName = cityName, countryId = countryCode)
            )
        } catch (t: Throwable) {
            Resource.Error(errorHandler.getError(t))
        }
    }

}