package com.example.forecastapplication

import android.app.Application
import com.example.forecastapplication.model.request.NetworkService

class WeatherApplication : Application() {
    val networkService: NetworkService =
        NetworkService()

    override fun onCreate() {
        super.onCreate()
    }

}