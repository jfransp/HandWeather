package com.example.data.di

import androidx.room.Room
import com.example.data.persistance.database.WeatherDataBase
import com.example.data.persistance.datastore.ConfigData
import com.example.data.persistance.datastore.DataStoreManager
import org.koin.dsl.module

val persistenceModule = module {

    //Room Database and DAOs
    single { Room.databaseBuilder(get(), WeatherDataBase::class.java, "cached_weather")
        .fallbackToDestructiveMigration()
        .build()
    }

    factory { provideCityWeatherDao(weatherDataBase = get()) }

    factory { provideCurrentLocationWeatherDao(weatherDataBase = get()) }

    factory { provideCurrentLocationForecastDao(weatherDataBase = get()) }

    //DataStore
    single { DataStoreManager(context = get()) }

    factory { ConfigData(manager = get()) }

}

fun provideCityWeatherDao(weatherDataBase: WeatherDataBase) = weatherDataBase.cityWeatherDao()

fun provideCurrentLocationWeatherDao(weatherDataBase: WeatherDataBase) = weatherDataBase.currentLocationWeatherDao()

fun provideCurrentLocationForecastDao(weatherDataBase: WeatherDataBase) = weatherDataBase.currentLocationForecastDao()