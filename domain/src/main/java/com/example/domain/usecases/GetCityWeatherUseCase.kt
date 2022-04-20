package com.example.domain.usecases

import com.example.domain.error.ErrorHandler
import com.example.domain.model.currentweather.CurrentWeather
import com.example.domain.repositories.CurrentWeatherRepository
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCityWeatherUseCase(
    private val currentWeatherRepository: CurrentWeatherRepository,
    private val errorHandler: ErrorHandler
) {

    suspend operator fun invoke(cityName: String, countryCode: String?): Resource<Flow<CurrentWeather>> {
        return try {
            Resource.Success(
                currentWeatherRepository.getCityWeather(cityName = cityName, countryId = countryCode)
            )
        } catch (t: Throwable) {
            Resource.Error(errorHandler.getError(t))
        }
    }

}
