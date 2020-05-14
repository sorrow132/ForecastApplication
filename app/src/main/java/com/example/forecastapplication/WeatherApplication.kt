package com.example.forecastapplication

import android.app.Application
import com.example.forecastapplication.di.components.AppComponent
import com.example.forecastapplication.di.components.DaggerAppComponent
import net.danlew.android.joda.JodaTimeAndroid

class WeatherApplication : Application() {
    val component: AppComponent = DaggerAppComponent
        .builder()
        .appContext(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }
}