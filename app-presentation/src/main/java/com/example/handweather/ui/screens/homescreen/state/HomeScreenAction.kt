package com.example.handweather.ui.screens.homescreen.state

import com.example.components.viewmodel.ComposeAction

sealed class HomeScreenAction: ComposeAction {
    object HomeActionPlaceHolder: HomeScreenAction()
}