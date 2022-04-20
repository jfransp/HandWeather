package com.example.handweather.ui.screens.locations

import com.example.components.viewmodel.ComposeStateViewModel
import com.example.domain.model.City
import com.example.handweather.ui.screens.locations.state.LocationsListAction
import com.example.handweather.ui.screens.locations.state.LocationsListState

class LocationsListScreenViewModel :
    ComposeStateViewModel<LocationsListState, LocationsListAction>(LocationsListState()) {

    fun onCityClicked(city: City) {
        setState { state ->
            state.copy(selectedCity = city)
        }
        sendAction {
            LocationsListAction.CityCardClicked(city = city)
        }
    }

}