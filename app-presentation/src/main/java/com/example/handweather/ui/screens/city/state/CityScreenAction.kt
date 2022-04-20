package com.example.handweather.ui.screens.city.state

import com.example.components.viewmodel.ComposeAction

sealed class CityScreenAction: ComposeAction {
    object PlaceHolderAction: CityScreenAction()
}