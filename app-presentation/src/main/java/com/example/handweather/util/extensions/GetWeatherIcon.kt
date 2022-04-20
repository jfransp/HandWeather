package com.example.handweather.util.extensions

import com.example.domain.model.currentweather.CurrentWeather
import com.example.handweather.R

fun CurrentWeather.getWeatherIcon(): Int {
    return when {
        description?.contains("clear sky") == true -> R.drawable.clear_sky
        description?.contains("few clouds") == true -> R.drawable.few_clouds
        description?.contains("scattered clouds") == true -> R.drawable.scattered_clouds
        description?.contains("broken clouds") == true -> R.drawable.broken_clouds
        description?.contains("shower rain") == true -> R.drawable.shower_rain
        description?.contains("rain") == true -> R.drawable.rain
        description?.contains("thunderstorm") == true -> R.drawable.thunderstorm
        description?.contains("snow") == true -> R.drawable.snow
        description?.contains("mist") == true -> R.drawable.mist
        else -> { R.drawable.clear_sky }
    }
}