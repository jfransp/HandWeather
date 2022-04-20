package com.example.domain.model

data class City(
    val id: Long? = null,
    val name: String? = null,
    val countryFullName: String? = null,
    val country: String? = null,
    val coordinates: Coordinates? = null
)