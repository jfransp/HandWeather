package com.example.handweather.ui.screens.locations.cities

import com.example.domain.model.City
import com.example.domain.model.Coordinates

//Not all the information in the city entity is necessary, but I tried to mirror the entity the API
//uses on its JSON list of official cities.
val cityList = listOf<City>(
    City(
        id = 6618607,
        name = "Paris",
        country = "FR",
        countryFullName = "France",
        coordinates = Coordinates(lat = 48.8592, lon = 2.3417)
    ),
    City(
        id = 2267057,
        name = "Lisbon",
        country = "PT",
        countryFullName = "Portugal",
        coordinates = Coordinates(lat = 38.716671, lon = -9.13333)
    ),
    City(
        id = 6359304,
        name = "Madrid",
        country = "ES",
        countryFullName = "Spain",
        coordinates = Coordinates(lat = 40.489349, lon = -3.68275)
    ),
    City(
        id = 2950159,
        name = "Berlin",
        country = "DE",
        countryFullName = "Germany",
        coordinates = Coordinates(lat = 52.524368, lon = 13.41053)
    ),
    City(
        id = 2618425,
        name = "Copenhagen",
        country = "DK",
        countryFullName = "Denmark",
        coordinates = Coordinates(lat = 55.675941, lon = 12.56553)
    ),
    City(
        id = 3169070,
        name = "Rome",
        country = "IT",
        countryFullName = "Italy",
        coordinates = Coordinates(lat = 41.894741, lon = 12.4839)
    ),
    City(
        id = 2643743,
        name = "London",
        country = "GB",
        countryFullName = "England",
        coordinates = Coordinates(lat = 51.50853, lon = -0.12574)
    ),
    City(
        id = 7778677,
        name = "Dublin",
        country = "IE",
        countryFullName = "Ireland",
        coordinates = Coordinates(lat = 53.355122, lon = -6.24922)
    ),
    City(
        id = 3067696,
        name = "Prague",
        country = "CZ",
        countryFullName = "Czech Republic",
        coordinates = Coordinates(lat = 50.088039, lon = 14.42076)
    ),
    City(
        id = 2761369,
        name = "Vienna",
        country = "AT",
        countryFullName = "Austria",
        coordinates = Coordinates(lat = 48.208488, lon = 16.37208)
    )

)
