package com.example.forecastapplication.view.ui.weather.future

import androidx.lifecycle.LiveData
import com.example.forecastapplication.WeatherState

interface IFutureWeatherViewModel {
    val state: LiveData<WeatherState>

    fun fetchInfo(city: String)
}