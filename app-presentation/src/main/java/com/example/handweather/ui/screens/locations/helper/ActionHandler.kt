package com.example.handweather.ui.screens.locations.helper

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import com.example.handweather.ui.screens.city.CityScreenViewModel
import com.example.handweather.ui.screens.locations.state.LocationsListAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
fun handleLocationListScreenAction(
    action: LocationsListAction,
    sheetScope: CoroutineScope,
    sheetState: ModalBottomSheetState,
    cityScreenViewModel: CityScreenViewModel
) {
    when (action) {
        is LocationsListAction.CityCardClicked -> {
            sheetScope.launch {
                cityScreenViewModel.onCityCardClicked(action.city)
                sheetState.animateTo(ModalBottomSheetValue.Expanded)
            }
        }
    }
}