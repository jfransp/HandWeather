package com.example.data.mappers

import com.example.data.network.entities.currentweathernetworkresponse.CurrentWeatherNetworkResponse
import com.example.data.persistance.database.entities.CityCurrentWeatherLocal
import com.example.data.persistance.database.entities.CurrentLocationCurrentWeatherLocal
import com.example.domain.model.currentweather.CurrentWeather

class WeatherMapper {

    //FROM NETWORK
    fun fromNetworkToDomain(input: CurrentWeatherNetworkResponse): CurrentWeather {
        return CurrentWeather(
            mainState = input.weather?.first()?.main,
            description = input.weather?.first()?.description,
            temperature = input.main?.temp,
            feelsLike = input.main?.feels_like,
            maxTemp = input.main?.temp_max,
            minTemp = input.main?.temp_min,
            humidity = input.main?.humidity,
            visibility = input.visibility,
            windSpeed = input.wind?.speed,
            cityName = input.name
        )
    }

    fun fromNetworkToLocalCurrentLocation(input: CurrentWeatherNetworkResponse): CurrentLocationCurrentWeatherLocal {
        return CurrentLocationCurrentWeatherLocal(
            mainState = input.weather?.first()?.main,
            description = input.weather?.first()?.description,
            temperature = input.main?.temp,
            feelsLike = input.main?.feels_like,
            maxTemp = input.main?.temp_max,
            minTemp = input.main?.temp_min,
            humidity = input.main?.humidity,
            visibility = input.visibility,
            windSpeed = input.wind?.speed,
            cityName = input.name
        )
    }

    fun fromNetworkToLocalCityLocation(input: CurrentWeatherNetworkResponse): CityCurrentWeatherLocal {
        return CityCurrentWeatherLocal(
            mainState = input.weather?.first()?.main,
            description = input.weather?.first()?.description,
            temperature = input.main?.temp,
            feelsLike = input.main?.feels_like,
            maxTemp = input.main?.temp_max,
            minTemp = input.main?.temp_min,
            humidity = input.main?.humidity,
            visibility = input.visibility,
            windSpeed = input.wind?.speed,
            cityName = input.name ?: ""
        )
    }


    //FROM LOCAL
    fun fromLocalCityCurrentWeatherToDomain(input: CityCurrentWeatherLocal): CurrentWeather {
        return CurrentWeather(
            mainState = input.mainState,
            description = input.description,
            temperature = input.temperature,
            feelsLike = input.feelsLike,
            maxTemp = input.maxTemp,
            minTemp = input.minTemp,
            humidity = input.humidity,
            visibility = input.visibility,
            windSpeed = input.windSpeed,
            cityName = input.cityName
        )
    }

    fun fromLocalCurrentLocationWeatherToDomain(input: CurrentLocationCurrentWeatherLocal): CurrentWeather {
        return CurrentWeather(
            mainState = input.mainState,
            description = input.description,
            temperature = input.temperature,
            feelsLike = input.feelsLike,
            maxTemp = input.maxTemp,
            minTemp = input.minTemp,
            humidity = input.humidity,
            visibility = input.visibility,
            windSpeed = input.windSpeed,
            cityName = input.cityName
        )
    }

    //FROM DOMAIN
    fun fromDomainToCityCurrentWeatherLocal(input: CurrentWeather): CityCurrentWeatherLocal {
        return CityCurrentWeatherLocal(
            cityName = input.cityName ?: "",
            mainState = input.mainState,
            description = input.description,
            temperature = input.temperature,
            feelsLike = input.feelsLike,
            maxTemp = input.maxTemp,
            minTemp = input.minTemp,
            humidity = input.humidity,
            visibility = input.visibility,
            windSpeed = input.windSpeed

        )
    }

    fun fromDomainToCurrentLocationCurrentWeatherLocal(input: CurrentWeather): CurrentLocationCurrentWeatherLocal {
        return CurrentLocationCurrentWeatherLocal(
            cityName = input.cityName ?: "",
            mainState = input.mainState,
            description = input.description,
            temperature = input.temperature,
            feelsLike = input.feelsLike,
            maxTemp = input.maxTemp,
            minTemp = input.minTemp,
            humidity = input.humidity,
            visibility = input.visibility,
            windSpeed = input.windSpeed
        )
    }

    companion object {
        fun provide(): WeatherMapper {
            return WeatherMapper()
        }
    }
}