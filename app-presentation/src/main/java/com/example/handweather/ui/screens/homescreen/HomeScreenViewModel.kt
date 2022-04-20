package com.example.handweather.ui.screens.homescreen

import androidx.lifecycle.viewModelScope
import com.example.components.viewmodel.ComposeStateViewModel
import com.example.domain.error.handleMessage
import com.example.domain.usecases.DoFetchCurrentLocationForecastUseCase
import com.example.domain.usecases.DoFetchCurrentWeatherUseCase
import com.example.domain.usecases.GetCurrentLocationForecastUseCase
import com.example.domain.usecases.GetCurrentLocationWeatherUseCase
import com.example.domain.util.Resource
import com.example.handweather.ui.screens.homescreen.state.ForecastErrorState
import com.example.handweather.ui.screens.homescreen.state.HomeScreenAction
import com.example.handweather.ui.screens.homescreen.state.HomeScreenDialogState
import com.example.handweather.ui.screens.homescreen.state.HomeScreenState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getCurrentLocationWeatherUseCase: GetCurrentLocationWeatherUseCase,
    private val doFetchCurrentWeatherUseCase: DoFetchCurrentWeatherUseCase,
    private val getCurrentLocationForecastUseCase: GetCurrentLocationForecastUseCase,
    private val doFetchCurrentLocationForecastUseCase: DoFetchCurrentLocationForecastUseCase
) :
    ComposeStateViewModel<HomeScreenState, HomeScreenAction>(HomeScreenState()) {

    init {
        loadCurrentWeatherData()
        loadForecastData()
    }

    private fun loadCurrentWeatherData() {
        viewModelScope.launch {
            setContentLoading(true)
            when (val currentWeatherResult = getCurrentLocationWeatherUseCase()) {
                is Resource.Success -> {
                    setContentLoading(false)
                    currentWeatherResult.data.collectLatest { weatherResult ->
                        setState { state ->
                            state.copy(currentLocationWeather = weatherResult)
                        }
                    }
                }
                is Resource.Error -> {
                    setContentLoading(false)
                    setDialogState(
                        dialogState = HomeScreenDialogState.GenericErrorDialog(
                            message = currentWeatherResult.handWeatherError.handleMessage()
                        )
                    )
                }
            }
        }
    }

    private fun loadForecastData() {
        viewModelScope.launch {
            setForecastLoading(true)
            when (val forecastResult = getCurrentLocationForecastUseCase()) {
                is Resource.Success -> {
                    setForecastLoading(false)
                    forecastResult.data.collectLatest { currentLocationForecastList ->
                        setState { state ->
                            state.copy(currentLocationForecasts = currentLocationForecastList)
                        }
                    }
                }
                is Resource.Error -> {
                    setForecastLoading(false)
                    setState { state ->
                        state.copy(
                            forecastErrorState = ForecastErrorState(
                                forecastResult.handWeatherError.handleMessage()
                            )
                        )
                    }
                }
            }
        }
    }

    fun fetchCurrentWeatherAndCurrentForecast() {
        viewModelScope.launch {
            doFetchCurrentWeatherUseCase()
            doFetchCurrentLocationForecastUseCase()
        }
    }

    private fun setContentLoading(value: Boolean) {
        setState { state ->
            state.copy(contentLoadState = value)
        }
    }

    //Forecast loadstate not working properly, need to refactor this.
    private fun setForecastLoading(value: Boolean) {
        setState { state ->
            state.copy(forecastLoadState = value)
        }
    }

    private fun setDialogState(dialogState: HomeScreenDialogState) {
        setState { state ->
            state.copy(dialogState = dialogState)
        }
    }

}
