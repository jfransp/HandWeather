package com.example.handweather.util.extensions

import com.example.domain.model.City
import com.example.handweather.R

fun City.getCityBackground(): Int {
    return when (this.name) {
        "Berlin" -> R.drawable.berlin_background
        "Paris" -> R.drawable.paris_background
        "Lisbon" -> R.drawable.lisbon_background
        "Madrid" -> R.drawable.madrid_background
        "Vienna" -> R.drawable.vienna_background
        "Dublin" -> R.drawable.dublin_background
        "London" -> R.drawable.london_background
        "Copenhagen" -> R.drawable.copenhagen_background
        "Prague" -> R.drawable.prague_background
        "Rome" -> R.drawable.rome_background
        else -> R.drawable.no_city
    }
}