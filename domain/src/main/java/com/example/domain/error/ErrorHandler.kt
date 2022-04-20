package com.example.domain.error

interface ErrorHandler {
    fun getError(throwable: Throwable): HandWeatherErrorEntity
}