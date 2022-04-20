package com.example.data.repositories

import com.example.data.datasources.LocalDataSource
import com.example.data.datasources.RemoteDataSource
import com.example.data.mappers.CoordinatesMapper
import com.example.data.mappers.ForecastMapper
import com.example.data.persistance.datastore.ConfigData
import com.example.domain.model.Coordinates
import com.example.domain.model.forecast.Forecast
import com.example.domain.repositories.ForecastRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import java.lang.NullPointerException

class ForecastRepositoryImpl(
    private val configData: ConfigData,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val forecastMapper: ForecastMapper,
    private val coordinatesMapper: CoordinatesMapper
) : ForecastRepository {

    override suspend fun fetchCurrentLocationForecast() {
        val result = forecastMapper.fromNetworkToCurrentLocationForecastLocal(
            remoteDataSource.fetchForecastByCoordinates(
                coordinatesMapper.fromModelToNetwork(
                    configData.deviceLastLocation.first()
                )
            )
        )

        if (!result.isNullOrEmpty()) {
            val resultSize = result.size
            val slicedResult = result.slice(0 until resultSize step 10)
            localDataSource.clearCurrentLocationForecastData().also {
                slicedResult.forEach { currentLocationForecastLocal ->
                    localDataSource.cacheCurrentLocationForecast(
                        currentLocationForecastLocal
                    )
                }
            }
        } else throw NullPointerException("Forecast data returned null")
    }

    override suspend fun getCurrentLocationForecast(): Flow<List<Forecast>> {
        return flow {
            localDataSource.getCurrentLocationForecast()
                .collect() { currentLocationForecastLocalList ->
                    emit(
                        if (currentLocationForecastLocalList.isNullOrEmpty()) {
                            getForecastByCoordinates(configData.deviceLastLocation.first())
                        } else {
                            currentLocationForecastLocalList.map { currentLocationForecastLocal ->
                                forecastMapper.fromCurrentLocationForecastLocalToDomain(
                                    currentLocationForecastLocal
                                )
                            }
                        }
                    )
                }
        }
    }


    override suspend fun getForecastByCoordinates(coordinates: Coordinates): List<Forecast> {
        val result = forecastMapper.fromNetworkToDomain(
            remoteDataSource.fetchForecastByCoordinates(
                coordinates = coordinatesMapper.fromModelToNetwork(coordinates)
            )
        )

        localDataSource.clearCurrentLocationForecastData().also {
            result.slice(result.indices step 10).forEach { currentLocationForecastLocal ->
                forecastMapper.fromDomainToCurrentLocationForecastLocal(
                    currentLocationForecastLocal
                )
            }
        }
        return result.slice(result.indices step 10)
    }

}
