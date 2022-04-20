package com.example.data.di

import com.example.data.util.ErrorHandlerImpl
import com.example.domain.error.ErrorHandler
import com.example.domain.usecases.*
import org.koin.dsl.module

val useCaseModule = module {

    //DoFetchCurrentWeatherUseCase
    factory {
        DoFetchCurrentWeatherUseCase(
            currentWeatherRepository = get()
        )
    }

    //GetCityWeatherUseCase
    factory {
        GetCityWeatherUseCase(
            currentWeatherRepository = get(),
            errorHandler = get()
        )
    }

    //GetCurrentWeatherUseCase
    factory {
        GetCurrentLocationWeatherUseCase(
            currentWeatherRepository = get(),
            errorHandler = get()
        )
    }

    //DoFetchCityWeatherUseCase
    factory {
        DoFetchCityWeatherUseCase(
            currentWeatherRepository = get(),
            errorHandler = get()
        )
    }

    //GetCurrentLocationForecastUseCase
    factory {
        GetCurrentLocationForecastUseCase(
            forecastRepository = get(),
            errorHandler = get()
        )
    }

    //DoFetchCurrentLocationForecastUseCase
    factory {
        DoFetchCurrentLocationForecastUseCase(
            forecastRepository = get()
        )
    }

    //DoSetLocationPreferencesUseCase
    factory {
        DoSetLocationPreferenceUseCase()
    }

    //...all depends on:
    //ErrorHandler
    factory<ErrorHandler> {
        ErrorHandlerImpl()
    }

}