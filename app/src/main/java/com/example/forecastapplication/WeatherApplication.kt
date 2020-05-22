package com.example.forecastapplication

import android.app.Application
import com.example.forecastapplication.di.components.AppComponent
import com.example.forecastapplication.di.components.DaggerAppComponent

class WeatherApplication : Application() {
    val component: AppComponent = DaggerAppComponent
        .builder()
        .appContext(this)
        .build()
}