package com.example.handweather.ui.di

import com.example.handweather.ui.screens.city.CityScreenViewModel
import com.example.handweather.ui.screens.homescreen.HomeScreenViewModel
import com.example.handweather.ui.screens.locations.LocationsListScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    //HomeScreenViewModel
    viewModel {
        HomeScreenViewModel(
            getCurrentLocationWeatherUseCase = get(),
            doFetchCurrentWeatherUseCase = get(),
            getCurrentLocationForecastUseCase = get(),
            doFetchCurrentLocationForecastUseCase = get()
        )
    }

    //LocationsListScreenViewModel
    viewModel {
        LocationsListScreenViewModel()
    }

    //CityScreenViewModel
    viewModel {
        CityScreenViewModel(
            getCityWeatherUseCase = get(),
            doFetchCityWeatherUseCase = get()
        )
    }

}