package com.example.data.persistance.datastore

import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.example.domain.model.Coordinates
import kotlinx.coroutines.flow.*

class ConfigData(private val manager: DataStoreManager) {

    companion object {
        private val DEVICE_LAST_LAT = doublePreferencesKey("device_last_latitude")
        private val DEVICE_LAST_LON = doublePreferencesKey("device_last_longitude")
        private val LOCATION_ACCURACY_PREFERENCE = intPreferencesKey("accuracy_preference")
    }

    private val deviceLastLat = manager().data
        .map { preferences ->
            preferences[DEVICE_LAST_LAT] ?: 0.0
        }

    private val deviceLastLon = manager().data
        .map { preferences ->
            preferences[DEVICE_LAST_LON] ?: 0.0
        }

    val locationAccuracyPreference = manager().data
        .map { preferences ->
            preferences[LOCATION_ACCURACY_PREFERENCE] ?: 100
        }

    val deviceLastLocation = deviceLastLat.combine(deviceLastLon) { lat, lon ->
        Coordinates(lat, lon)
    }

    //SET
    suspend fun setDeviceLocation(lat: Double, lon: Double) {
        manager().edit { settings ->
            settings[DEVICE_LAST_LAT] = lat
            settings[DEVICE_LAST_LON] = lon
        }
    }
}
