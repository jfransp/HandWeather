package com.example.handweather.ui.screens.city.state

import com.example.components.viewmodel.ComposeState
import com.example.domain.model.City
import com.example.domain.model.currentweather.CurrentWeather

data class CityScreenState(
    val contentLoadState: Boolean = false,
    val cityCurrentWeather: CurrentWeather? = null,
    val dialogState: CityScreenDialogState? = null,
    val fetchingError: FetchingErrorState? = null,
    val currentCity: City? = null
) : ComposeState