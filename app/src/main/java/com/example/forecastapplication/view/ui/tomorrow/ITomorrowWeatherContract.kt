package com.example.forecastapplication.view.ui.tomorrow

import androidx.lifecycle.LiveData
import com.example.forecastapplication.WeatherState

interface ITomorrowWeatherContract {

    val state: LiveData<WeatherState>

    fun fetchInfo(city: String)
}