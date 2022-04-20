package com.example.data.mappers

import com.example.data.network.entities.forecastnetworkresponse.ForecastNetworkResponse
import com.example.data.persistance.database.entities.CurrentLocationForecastLocal
import com.example.domain.model.forecast.Forecast
import java.lang.NullPointerException

class ForecastMapper {

    //FROM NETWORK
    fun fromNetworkToDomain(input: ForecastNetworkResponse): List<Forecast> {
        return input.list?.map { forecastNetwork ->
            Forecast(
                cityName = input.city?.name,
                countryCode = input.city?.country,
                dt = forecastNetwork.dt,
                dt_txt = forecastNetwork.dt_txt,
                temp = forecastNetwork.main?.temp,
                feelsLike = forecastNetwork.main?.feels_like,
                tempMin = forecastNetwork.main?.temp_min,
                tempMax = forecastNetwork.main?.temp_max,
                humidity = forecastNetwork.main?.humidity,
                main = forecastNetwork.weather?.first()?.main,
                description = forecastNetwork.weather?.first()?.description
            )
        } ?: throw NullPointerException("forecast list returned null from api")
    }

    fun fromNetworkToCurrentLocationForecastLocal(input: ForecastNetworkResponse): List<CurrentLocationForecastLocal>? {
        return input.list?.map { forecastNetwork ->
            CurrentLocationForecastLocal(
                cityName = input.city?.name,
                countryCode = input.city?.country,
                dt = forecastNetwork.dt,
                dt_txt = forecastNetwork.dt_txt,
                temp = forecastNetwork.main?.temp,
                feelsLike = forecastNetwork.main?.feels_like,
                tempMin = forecastNetwork.main?.temp_min,
                tempMax = forecastNetwork.main?.temp_max,
                humidity = forecastNetwork.main?.humidity,
                main = forecastNetwork.weather?.first()?.main,
                description = forecastNetwork.weather?.first()?.description
            )
        }
    }

    //FROM LOCAL
    fun fromCurrentLocationForecastLocalToDomain(input: CurrentLocationForecastLocal): Forecast {
        return Forecast(
            cityName = input.cityName,
            countryCode = input.countryCode,
            dt = input.dt,
            dt_txt = input.dt_txt,
            temp = input.temp,
            feelsLike = input.feelsLike,
            tempMin = input.tempMin,
            tempMax = input.tempMax,
            humidity = input.humidity,
            main = input.main,
            description = input.description
        )
    }

    //FROM DOMAIN
    fun fromDomainToCurrentLocationForecastLocal(input: Forecast) : CurrentLocationForecastLocal {
        return CurrentLocationForecastLocal(
            cityName = input.cityName,
            countryCode = input.countryCode,
            dt = input.dt,
            dt_txt = input.dt_txt,
            temp = input.temp,
            feelsLike = input.feelsLike,
            tempMin = input.tempMin,
            tempMax = input.tempMax,
            humidity = input.humidity,
            main = input.main,
            description = input.description
        )
    }

    companion object {
        fun provide(): ForecastMapper {
            return ForecastMapper()
        }
    }

}
