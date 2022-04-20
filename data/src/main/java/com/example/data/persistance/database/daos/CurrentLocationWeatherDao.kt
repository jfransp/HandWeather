package com.example.data.persistance.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.persistance.database.entities.CurrentLocationCurrentWeatherLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentLocationWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheCurrentLocationCurrentWeather(weather: CurrentLocationCurrentWeatherLocal)

    //Not sure if this functions is ever necessary
    @Query("DELETE FROM cached_current_location_weather WHERE id LIKE :id")
    suspend fun deleteCurrentLocationCachedWeather(id: String)

    @Query("SELECT * FROM cached_current_location_weather WHERE id LIKE :id")
    fun getCachedCurrentLocationWeather(id: String): Flow<List<CurrentLocationCurrentWeatherLocal>>

}