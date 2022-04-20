package com.example.domain.usecases

import com.example.domain.error.ErrorHandler
import com.example.domain.model.forecast.Forecast
import com.example.domain.repositories.ForecastRepository
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCurrentLocationForecastUseCase(
    private val forecastRepository: ForecastRepository,
    private val errorHandler: ErrorHandler
) {

    suspend operator fun invoke(): Resource<Flow<List<Forecast>>> {
        return try {
            Resource.Success(
                forecastRepository.getCurrentLocationForecast()
            )
        } catch (t: Throwable) {
            Resource.Error(errorHandler.getError(t))
        }
    }

}