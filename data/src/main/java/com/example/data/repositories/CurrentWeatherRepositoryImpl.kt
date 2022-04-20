package com.example.data.repositories

import com.example.data.datasources.LocalDataSource
import com.example.data.datasources.RemoteDataSource
import com.example.data.mappers.CoordinatesMapper
import com.example.data.mappers.WeatherMapper
import com.example.data.persistance.datastore.ConfigData
import com.example.domain.model.Coordinates
import com.example.domain.model.currentweather.CurrentWeather
import com.example.domain.repositories.CurrentWeatherRepository
import kotlinx.coroutines.flow.*

class CurrentWeatherRepositoryImpl(
    private val configData: ConfigData,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val weatherMapper: WeatherMapper,
    private val coordinatesMapper: CoordinatesMapper
) : CurrentWeatherRepository {

    override suspend fun fetchCurrentLocationWeather() {
        localDataSource.cacheCurrentLocationCurrentWeather(
            weatherMapper.fromNetworkToLocalCurrentLocation(
                remoteDataSource.fetchLocationWeatherByCoordinates(
                    coordinatesMapper.fromModelToNetwork(
                        configData.deviceLastLocation.first()
                    )
                )
            )
        )
    }

    override suspend fun getCurrentLocationWeather(): Flow<CurrentWeather> {
        return flow {
            localDataSource.getCurrentLocationCurrentWeather()
                .collect { currentLocationWeatherList ->
                    emit(
                        if (currentLocationWeatherList.isNullOrEmpty()) {
                            getCurrentWeatherByCoordinates(configData.deviceLastLocation.first())
                        } else {
                            weatherMapper.fromLocalCurrentLocationWeatherToDomain(
                                currentLocationWeatherList.first()
                            )
                        }
                    )
                }
        }
    }

    override suspend fun fetchCityWeather(cityName: String, countryId: String) {
        localDataSource.cacheCityWeather(
            weatherMapper.fromNetworkToLocalCityLocation(
                remoteDataSource.fetchCityWeatherByCityName(
                    cityName = cityName,
                    countryId = countryId
                )
            )
        )
    }

    override suspend fun getCityWeather(cityName: String, countryId: String?): Flow<CurrentWeather> {
        return flow {
            localDataSource.getCityWeather(cityName = cityName)
                .collect { cityWeatherList ->
                    emit(
                        if (cityWeatherList.isNullOrEmpty()) {
                            getCurrentWeatherByCityNameAndCountryId(cityName = cityName, countryId = countryId)
                        } else {
                            weatherMapper.fromLocalCityCurrentWeatherToDomain(
                                cityWeatherList.first()
                            )
                        }
                    )
                }
        }
    }

    override suspend fun getCurrentWeatherByCoordinates(coordinates: Coordinates): CurrentWeather {
        return weatherMapper.fromNetworkToDomain(
            remoteDataSource.fetchLocationWeatherByCoordinates(
                coordinates = coordinatesMapper.fromModelToNetwork(coordinates)
            )
        ).also { currentWeather ->
            localDataSource.cacheCurrentLocationCurrentWeather(
                weatherMapper.fromDomainToCurrentLocationCurrentWeatherLocal(currentWeather)
            )
        }
    }

    override suspend fun getCurrentWeatherByCityNameAndCountryId(
        cityName: String,
        countryId: String?
    ): CurrentWeather {
        return weatherMapper.fromNetworkToDomain(
            remoteDataSource.fetchCityWeatherByCityName(
                cityName = cityName,
                countryId = countryId
            )
        ).also { currentWeather ->
            localDataSource.cacheCityWeather(
                weatherMapper.fromDomainToCityCurrentWeatherLocal(currentWeather)
            )
        }
    }

}
