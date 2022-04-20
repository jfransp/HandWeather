package com.example.handweather.ui.screens.city.state

sealed class CityScreenDialogState {
    data class GenericErrorDialog(val message: String): CityScreenDialogState()
}