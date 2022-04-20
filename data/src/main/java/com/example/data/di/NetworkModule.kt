package com.example.data.di

import com.example.data.network.AppClient
import com.example.data.network.AppClientImpl
import com.example.data.network.services.ForecastApiClient
import com.example.data.network.services.WeatherApiClient
import org.koin.dsl.module

val networkModule = module {

    //Weather API Client
    factory {
        WeatherApiClient(
            client = get()
        )
    }

    factory {
        ForecastApiClient(
            client = get()
        )
    }

    //Http Client
    single<AppClient> {
        AppClientImpl()
    }

}