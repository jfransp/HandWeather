package com.example.handweather.ui.screens.homescreen.state

sealed class HomeScreenDialogState {
    data class GenericErrorDialog(val message: String): HomeScreenDialogState()
}