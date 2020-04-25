package com.example.forecastapplication.view.ui.current

import androidx.lifecycle.LiveData
import com.example.forecastapplication.WeatherState

interface ICurrentWeatherContract {

    val state: LiveData<WeatherState>

    fun fetchWeather(city: String)
}