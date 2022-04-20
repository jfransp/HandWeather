package com.example.handweather

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.data.persistance.datastore.ConfigData
import com.example.handweather.locationservice.DeviceLocationManager
import com.example.handweather.ui.navigation.HandWeatherNavigationIds
import com.example.handweather.ui.navigation.slideComposable
import com.example.handweather.ui.screens.homescreen.HomeScreen
import com.example.handweather.ui.screens.locations.LocationsListScreen
import com.example.handweather.ui.theme.HandWeatherTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.android.ext.android.inject

class HandWeatherActivity : ComponentActivity() {

    private val configData: ConfigData by inject()

    private lateinit var locationManager: DeviceLocationManager

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun HandWeatherAppContent(
        locationManager: DeviceLocationManager
    ) {

        val navController = rememberAnimatedNavController()

        AnimatedNavHost(
            navController = navController,
            startDestination = HandWeatherNavigationIds.HomeScreen.route
        ) {

            slideComposable(
                route = HandWeatherNavigationIds.HomeScreen.route,
            ) {
                HomeScreen(
                    locationManager = locationManager,
                    onCityWeathersClicked = {
                        navController.navigate(HandWeatherNavigationIds.LocationsListScreen.route)
                    }
                )
            }

            slideComposable(
                route = HandWeatherNavigationIds.LocationsListScreen.route
            ) {
                LocationsListScreen(
                    onBackClicked = {
                        navController.popBackStack()
                    }
                )
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationManager = DeviceLocationManager(activity = this, configData = configData)

        setContent {
            HandWeatherTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    HandWeatherAppContent(locationManager = locationManager)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    locationManager.fetchLocation()
                } else {
                    finish()
                }
                return
            }
            else -> {}
        }
    }


}
