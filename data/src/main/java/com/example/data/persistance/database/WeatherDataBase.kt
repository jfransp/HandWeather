package com.example.data.persistance.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.persistance.database.daos.CityWeatherDao
import com.example.data.persistance.database.daos.CurrentLocationForecastDao
import com.example.data.persistance.database.daos.CurrentLocationWeatherDao
import com.example.data.persistance.database.entities.CityCurrentWeatherLocal
import com.example.data.persistance.database.entities.CurrentLocationCurrentWeatherLocal
import com.example.data.persistance.database.entities.CurrentLocationForecastLocal

@Database(
    entities = [
        CurrentLocationCurrentWeatherLocal::class,
        CityCurrentWeatherLocal::class,
        CurrentLocationForecastLocal::class
    ],
    version = 1,
    exportSchema = false
)
abstract class WeatherDataBase : RoomDatabase() {

    abstract fun cityWeatherDao(): CityWeatherDao

    abstract fun currentLocationWeatherDao(): CurrentLocationWeatherDao

    abstract fun currentLocationForecastDao(): CurrentLocationForecastDao

}
