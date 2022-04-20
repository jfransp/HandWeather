package com.example.handweather.util.extensions

import com.example.domain.model.City
import com.example.handweather.R

fun City.getCityIcon(): Int {
    return when (this.name) {
        "Berlin" -> R.drawable.ic_berlin
        "Paris" -> R.drawable.ic_paris
        "Lisbon" -> R.drawable.ic_lisbon
        "Madrid" -> R.drawable.ic_madrid
        "Vienna" -> R.drawable.ic_vienna
        "Dublin" -> R.drawable.ic_dublin
        "London" -> R.drawable.ic_london
        "Copenhagen" -> R.drawable.ic_copenhagen
        "Prague" -> R.drawable.ic_prague
        "Rome" -> R.drawable.ic_rome
        else -> R.drawable.ic_copenhagen
    }
}