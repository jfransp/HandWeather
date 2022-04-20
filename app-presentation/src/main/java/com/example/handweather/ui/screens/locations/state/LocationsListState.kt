package com.example.handweather.ui.screens.locations.state

import com.example.components.viewmodel.ComposeState
import com.example.domain.model.City
import com.example.handweather.ui.screens.locations.cities.cityList

data class LocationsListState(
    val contentLoadState: Boolean = false,
    val cities: List<City> = cityList,
    val selectedCity: City? = null
) : ComposeState
