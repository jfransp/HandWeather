package com.example.data.util

import com.example.domain.error.HandWeatherErrorEntity
import com.example.domain.error.ErrorHandler
import io.ktor.client.features.*
import io.ktor.http.*
import java.lang.NullPointerException

class ErrorHandlerImpl : ErrorHandler {

    override fun getError(throwable: Throwable): HandWeatherErrorEntity {
        val errorEntity = when (throwable) {
            is ClientRequestException -> {
                val response = throwable.response
                when (response.status) {
                    HttpStatusCode.Unauthorized -> HandWeatherErrorEntity.ApiError.Unauthorized
                    HttpStatusCode.Forbidden -> HandWeatherErrorEntity.ApiError.Unauthorized
                    HttpStatusCode.NotFound -> HandWeatherErrorEntity.ApiError.NotFound
                    HttpStatusCode.RequestTimeout, HttpStatusCode.GatewayTimeout -> HandWeatherErrorEntity.ApiError.Timeout
                    HttpStatusCode.GatewayTimeout -> HandWeatherErrorEntity.ApiError.Timeout
                    else -> {
                        HandWeatherErrorEntity.ApiError.Unknown
                    }
                }

            }
            else -> {
                when (throwable) {
                    is NullPointerException -> HandWeatherErrorEntity.OtherError.NullPointerException
                    else -> HandWeatherErrorEntity.Unknown()
                }
                HandWeatherErrorEntity.Unknown()
            }
        }
        return errorEntity
    }
}


