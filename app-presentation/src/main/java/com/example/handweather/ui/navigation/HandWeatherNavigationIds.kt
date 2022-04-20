package com.example.handweather.ui.navigation

sealed class HandWeatherNavigationIds(val route: String) {
    object HomeScreen: HandWeatherNavigationIds(route = "home_screen")
    object LocationsListScreen: HandWeatherNavigationIds(route = "locations_list_screen")

    //Not used, this screen is showed through a Bottom Sheet on the list screen
    object CityScreen: HandWeatherNavigationIds(route = "city_screen")
}
