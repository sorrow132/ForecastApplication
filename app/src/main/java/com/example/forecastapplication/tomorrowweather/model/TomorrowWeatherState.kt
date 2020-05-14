package com.example.forecastapplication.tomorrowweather.model

sealed class TomorrowWeatherState {

    object Loading : TomorrowWeatherState()

    data class LoadTomorrowInfo(
        val weather: List<TomorrowWeatherModel>
    ) : TomorrowWeatherState()

    data class Error(
        val error: Throwable
    ) : TomorrowWeatherState()

}