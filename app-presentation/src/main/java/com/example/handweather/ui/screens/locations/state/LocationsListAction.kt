package com.example.handweather.ui.screens.locations.state

import com.example.components.viewmodel.ComposeAction
import com.example.domain.model.City

sealed class LocationsListAction: ComposeAction {
    data class CityCardClicked(val city: City): LocationsListAction()
}
