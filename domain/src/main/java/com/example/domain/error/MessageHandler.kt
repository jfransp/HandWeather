package com.example.domain.error

fun ErrorEntity.handleMessage(): String {

    return when (this) {
        is HandWeatherErrorEntity.ApiError.Unknown -> "An unknown error ocurred while request the necessary information."
        is HandWeatherErrorEntity.ApiError.NotFound -> "Result not found, try again."
        is HandWeatherErrorEntity.ApiError.Timeout -> "Endpoint request timed out."
        is HandWeatherErrorEntity.ApiError.Unauthorized -> "You don't have permission to access this information."
        is HandWeatherErrorEntity.Unknown -> "An unknown error ocurred."
        is HandWeatherErrorEntity.OtherError.NullPointerException -> "A necessary data wasn't found."
        else -> { "An unknown error ocurred." }
    }
}