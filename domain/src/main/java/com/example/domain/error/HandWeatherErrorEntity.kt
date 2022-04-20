package com.example.domain.error

sealed class HandWeatherErrorEntity(override var code: Int): ErrorEntity {

    sealed class ApiError(code: Int = 400): HandWeatherErrorEntity(code) {
        object NotFound : ApiError(402)
        object Timeout : ApiError(403)
        object Unknown : ApiError(404)
        object Unauthorized : ApiError(406)
    }

    sealed class OtherError(code: Int = 500): HandWeatherErrorEntity(code) {
        object NullPointerException: OtherError(code = 501)
    }

    class Unknown(code: Int = 101) : HandWeatherErrorEntity(code)

}

