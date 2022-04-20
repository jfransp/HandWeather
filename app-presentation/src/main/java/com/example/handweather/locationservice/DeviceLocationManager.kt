package com.example.handweather.locationservice

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.example.data.persistance.datastore.ConfigData
import com.example.domain.model.Coordinates
import com.google.android.gms.location.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class DeviceLocationManager(
    private val activity: ComponentActivity,
    private val configData: ConfigData
) {
    //OnUpdateEvent Callback
    var onLocationUpdateEventCallback: (() -> Unit)? = null

    //Private
    private var fusedLocationProviderClient: FusedLocationProviderClient
    private var locationRequest: LocationRequest
    private var locationCallback: LocationCallback
    private var currentLocationPriorityPreference: Int = LocationRequest.PRIORITY_HIGH_ACCURACY

    //Public
    private var _currentDeviceCoordinates: MutableStateFlow<Coordinates?> = MutableStateFlow(null)
    var currentDeviceCoordinates: StateFlow<Coordinates?> =
        _currentDeviceCoordinates

    init {
        activity.lifecycleScope.launch {
            configData.apply {
                deviceLastLocation.collectLatest { coordinates ->
                    _currentDeviceCoordinates.value = coordinates
                }
                locationAccuracyPreference.collectLatest { currentPriorityPreference ->
                    currentLocationPriorityPreference = currentPriorityPreference
                }
            }
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

        locationRequest = LocationRequest().apply {
            interval = TimeUnit.SECONDS.toMillis(30)
            fastestInterval = TimeUnit.SECONDS.toMillis(10)
            maxWaitTime = TimeUnit.MINUTES.toMillis(2)

            priority = currentLocationPriorityPreference
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                activity.lifecycleScope.launch {
                    configData.setDeviceLocation(
                        lat = locationResult.lastLocation.latitude,
                        lon = locationResult.lastLocation.longitude
                    )
                    onLocationUpdateEventCallback?.invoke()
                }

            }
        }

        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()!!
        )
    }

    fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
            return
        } else {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    if (location != null) {
                        activity.lifecycleScope.launch {
                            configData.setDeviceLocation(
                                lat = location.latitude,
                                lon = location.longitude
                            )
                            onLocationUpdateEventCallback?.invoke()
                        }
                    }
                }
        }
    }

    private fun requestLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                100
            )
        }
    }

    fun setOnLocationUpdateCallback(onLocationUpdate: () -> Unit) {
        onLocationUpdateEventCallback = onLocationUpdate
    }

    fun clearLocationUpdateCallback() {
        onLocationUpdateEventCallback = null
    }

}
