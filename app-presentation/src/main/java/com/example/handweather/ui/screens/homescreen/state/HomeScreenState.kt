package com.example.handweather.ui.screens.homescreen.state

import com.example.components.viewmodel.ComposeState
import com.example.domain.model.currentweather.CurrentWeather
import com.example.domain.model.forecast.Forecast

data class HomeScreenState(
    var contentLoadState: Boolean = false,
    var forecastLoadState: Boolean = false,
    var forecastErrorState: ForecastErrorState? = null,
    var currentLocationWeather: CurrentWeather? = null,
    var currentLocationForecasts: List<Forecast>? = null,
    var dialogState: HomeScreenDialogState? = null
) : ComposeState