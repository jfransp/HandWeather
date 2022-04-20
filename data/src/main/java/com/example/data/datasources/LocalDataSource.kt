package com.example.data.datasources

import android.util.Log
import com.example.data.persistance.database.daos.CityWeatherDao
import com.example.data.persistance.database.daos.CurrentLocationForecastDao
import com.example.data.persistance.database.daos.CurrentLocationWeatherDao
import com.example.data.persistance.database.entities.CityCurrentWeatherLocal
import com.example.data.persistance.database.entities.CurrentLocationCurrentWeatherLocal
import com.example.data.persistance.database.entities.CurrentLocationForecastLocal
import com.example.data.util.Constants
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val cityWeatherDao: CityWeatherDao,
    private val currentLocationWeatherDao: CurrentLocationWeatherDao,
    private val currentLocationForecastDao: CurrentLocationForecastDao
) {

    //CURRENT WEATHER CACHING
    suspend fun cacheCurrentLocationCurrentWeather(weather: CurrentLocationCurrentWeatherLocal) {
        currentLocationWeatherDao.cacheCurrentLocationCurrentWeather(weather = weather)
    }

    suspend fun cacheCityWeather(weather: CityCurrentWeatherLocal) {
        cityWeatherDao.cacheCurrentWeather(weather = weather)
        Log.v("cacheCityWeather", "function called")
    }

    //CURRENT WEATHER GETTING
    fun getCurrentLocationCurrentWeather(): Flow<List<CurrentLocationCurrentWeatherLocal>> {
        return currentLocationWeatherDao.getCachedCurrentLocationWeather(Constants.CACHED_SINGLE_ITEM_ID)
    }

    fun getCityWeather(cityName: String): Flow<List<CityCurrentWeatherLocal>> {
        return cityWeatherDao.getCachedLocationWeatherByCityName(cityName = cityName)
    }

    //FORECAST CACHING
    suspend fun cacheCurrentLocationForecast(forecast: CurrentLocationForecastLocal) {
        currentLocationForecastDao.cacheCurrentLocationForecast(forecast)
    }

    suspend fun clearCurrentLocationForecastData() {
        currentLocationForecastDao.clearTable()
    }

    //FORECAST GETTING
    fun getCurrentLocationForecast(): Flow<List<CurrentLocationForecastLocal>> {
        return currentLocationForecastDao.getCachedCurrentLocationWeather()
    }

}
