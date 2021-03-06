package com.example.handweather.ui.screens.city

import androidx.lifecycle.viewModelScope
import com.example.components.viewmodel.ComposeStateViewModel
import com.example.domain.error.handleMessage
import com.example.domain.model.City
import com.example.domain.usecases.DoFetchCityWeatherUseCase
import com.example.domain.usecases.GetCityWeatherUseCase
import com.example.domain.util.Resource
import com.example.handweather.ui.screens.city.state.CityScreenAction
import com.example.handweather.ui.screens.city.state.CityScreenDialogState
import com.example.handweather.ui.screens.city.state.CityScreenState
import com.example.handweather.ui.screens.city.state.FetchingErrorState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CityScreenViewModel(
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    private val doFetchCityWeatherUseCase: DoFetchCityWeatherUseCase,
) : ComposeStateViewModel<CityScreenState, CityScreenAction>(CityScreenState()) {

    private var cityDataJob: Job? = null

    fun fetchCityWeather(city: City? = null) {
        viewModelScope.launch {
            doFetchCityWeatherUseCase(
                cityName = city?.name ?: state.value.currentCity?.name ?: "",
                 countryCode = city?.country ?: state.value.currentCity?.country ?: ""
            ).also { result ->
                if (result is Resource.Error) {
                    setState { state ->
                        state.copy(
                            fetchingError = FetchingErrorState(
                                message = result.handWeatherError.handleMessage()
                            )
                        )
                    }
                    delay(2000)
                    setState { state ->
                        state.copy(fetchingError = null)
                    }
                }
            }
        }
    }


    private fun getCityWeather(city: City?) {
        cityDataJob?.cancel()
        cityDataJob = viewModelScope.launch {
            setLoadState(true)
            if (city != null) {
                when (val result =
                    getCityWeatherUseCase(
                        cityName = city.name!!,
                        countryCode = city.country
                    )
                ) {
                    is Resource.Success -> {
                        result.data.collectLatest { currentWeather ->
                            setState { state ->
                                state.copy(cityCurrentWeather = currentWeather)
                            }
                            setLoadState(false)
                        }.also { fetchCityWeather(city) }
                    }
                    is Resource.Error -> {
                        setDialogState(
                            CityScreenDialogState.GenericErrorDialog(
                                result.handWeatherError.handleMessage()
                            )
                        )
                        setLoadState(false)
                    }
                }
            }
        }
    }

    fun onCityCardClicked(city: City) {
        setState { state ->
            state.copy(currentCity = city)
        }
        getCityWeather(city)
    }

    private fun setDialogState(dialogState: CityScreenDialogState) {
        setState { state ->
            state.copy(dialogState = dialogState)
        }
    }

    private fun setLoadState(value: Boolean) {
        setState { state ->
            state.copy(contentLoadState = value)
        }
    }

}