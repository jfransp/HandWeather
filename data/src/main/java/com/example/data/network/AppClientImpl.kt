package com.example.data.network

import com.example.data.util.Constants.Companion.API_KEY
import com.example.data.util.Constants.Companion.CONNECT_TIMEOUT_MILLIS
import com.example.data.util.Constants.Companion.REQUEST_TIMEOUT_MILLIS
import com.example.data.util.Constants.Companion.SOCKET_TIMEOUT_MILLIS
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.logger.Level

class AppClientImpl: AppClient {

    override val client: HttpClient =
        HttpClient(OkHttp) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
            engine {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(loggingInterceptor)
            }
            install(HttpTimeout) {
                connectTimeoutMillis = CONNECT_TIMEOUT_MILLIS
                socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
                requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
            }
        }

    override fun getApiKey(): String {
        return API_KEY
    }

}
