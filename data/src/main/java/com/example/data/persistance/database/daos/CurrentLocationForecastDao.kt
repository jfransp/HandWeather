package com.example.data.persistance.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.persistance.database.entities.CurrentLocationForecastLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentLocationForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun cacheCurrentLocationForecast(weather: CurrentLocationForecastLocal)

    @Query("DELETE FROM cached_current_location_forecast")
    suspend fun clearTable()

    @Query("SELECT * FROM cached_current_location_forecast")
    fun getCachedCurrentLocationWeather(): Flow<List<CurrentLocationForecastLocal>>

}