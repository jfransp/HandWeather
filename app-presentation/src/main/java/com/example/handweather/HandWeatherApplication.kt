package com.example.handweather

import android.app.Application
import com.example.data.di.*
import com.example.handweather.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class HandWeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@HandWeatherApplication)

            modules(
                useCaseModule,
                networkModule,
                repositoryModule,
                viewModelModule,
                persistenceModule,
                appModule
            )
        }
    }
}