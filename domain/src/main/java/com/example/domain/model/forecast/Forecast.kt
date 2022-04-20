package com.example.domain.model.forecast

data class Forecast(
    var cityName: String? = null,
    var countryCode: String? = null,
    var dt: Long? = null,
    var dt_txt: String? = null,
    var temp: Double? = null,
    var feelsLike: Double? = null,
    var tempMin: Double? = null,
    var tempMax: Double? = null,
    var humidity: Int? = null,
    var main: String? = null,
    var description: String? = null
)