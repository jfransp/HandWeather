package com.example.domain.usecases

import com.example.domain.error.ErrorHandler
import com.example.domain.model.currentweather.CurrentWeather
import com.example.domain.repositories.CurrentWeatherRepository
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCurrentLocationWeatherUseCase(
    private val currentWeatherRepository: CurrentWeatherRepository,
    private val errorHandler: ErrorHandler
) {

    suspend operator fun invoke(): Resource<Flow<CurrentWeather>> {
        return try {
            Resource.Success(
                currentWeatherRepository.getCurrentLocationWeather()
            )
        } catch (t: Throwable) {
            Resource.Error(errorHandler.getError(t))
        }
    }


}
