package com.example.data.network

import io.ktor.client.*

interface AppClient {

    operator fun invoke(): HttpClient {
        return client
    }

    val client: HttpClient

    fun getApiKey(): String
}