package com.example.data.persistance.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.persistance.database.entities.CityCurrentWeatherLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface CityWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheCurrentWeather(weather: CityCurrentWeatherLocal)

    //Not sure if this functions is ever necessary
    @Query("DELETE FROM cached_city_weather WHERE cityName LIKE :cityName")
    suspend fun deleteCachedWeather(cityName: String)

    @Query("SELECT * FROM cached_city_weather WHERE cityName LIKE :cityName")
    fun getCachedLocationWeatherByCityName(cityName: String): Flow<List<CityCurrentWeatherLocal>>

}