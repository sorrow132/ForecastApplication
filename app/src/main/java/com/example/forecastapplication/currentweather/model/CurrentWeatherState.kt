package com.example.forecastapplication.currentweather.model

sealed class CurrentWeatherState {

    object Loading : CurrentWeatherState()

    data class LoadInfo(
        val weather: CurrentWeatherModel
    ) : CurrentWeatherState()

    data class Error(
        val error: Throwable
    ) : CurrentWeatherState()
}