package com.example.data.di

import com.example.data.datasources.LocalDataSource
import com.example.data.datasources.RemoteDataSource
import org.koin.dsl.module

val appModule = module {

    //LocalDataSource
    factory {
        LocalDataSource(
            cityWeatherDao = get(),
            currentLocationWeatherDao = get(),
            currentLocationForecastDao = get()
        )
    }

    //RemoteDataSource
    factory {
        RemoteDataSource(
            currentWeatherApiClient = get(),
            forecastApiClient = get()
        )
    }

}
