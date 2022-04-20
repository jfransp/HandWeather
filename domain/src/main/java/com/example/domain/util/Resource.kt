package com.example.domain.util

import com.example.domain.error.HandWeatherErrorEntity

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val handWeatherError: HandWeatherErrorEntity) : Resource<T>()
}