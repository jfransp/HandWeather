package com.example.data.util

class Constants {
    companion object {
        const val BASE_URL = "https://api.openweathermap.org"
        const val WEATHER_PATH = "data/2.5/weather"
        const val FORECAST_PATH = "data/2.5/forecast"
        const val API_KEY = "77c39b9ab64d2dc4bf8c2db1c929180d"
        const val UNIT_OF_MEASUREMENT = "metric"

        const val CONNECT_TIMEOUT_MILLIS = 30 * 1000L
        const val REQUEST_TIMEOUT_MILLIS = 30 * 1000L
        const val SOCKET_TIMEOUT_MILLIS = 30 * 1000L

        //funny looking solution but...it works.
        const val CACHED_SINGLE_ITEM_ID = "current"
    }
}