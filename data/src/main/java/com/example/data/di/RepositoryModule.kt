package com.example.data.di

import com.example.data.mappers.CoordinatesMapper
import com.example.data.mappers.ForecastMapper
import com.example.data.mappers.WeatherMapper
import com.example.data.repositories.CurrentWeatherRepositoryImpl
import com.example.data.repositories.ForecastRepositoryImpl
import com.example.domain.repositories.CurrentWeatherRepository
import com.example.domain.repositories.ForecastRepository
import org.koin.dsl.module

val repositoryModule = module {

    //Weather Repository
    factory<CurrentWeatherRepository> {
        CurrentWeatherRepositoryImpl(
            coordinatesMapper = CoordinatesMapper.provide(),
            weatherMapper = WeatherMapper.provide(),
            configData = get(),
            localDataSource = get(),
            remoteDataSource = get()
        )
    }

    //Forecast Repository
    factory<ForecastRepository> {
        ForecastRepositoryImpl(
            configData = get(),
            localDataSource = get(),
            remoteDataSource = get(),
            coordinatesMapper = CoordinatesMapper.provide(),
            forecastMapper = ForecastMapper.provide()
        )
    }

}