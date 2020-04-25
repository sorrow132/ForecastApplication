package com.example.forecastapplication

import com.example.forecastapplication.model.CurrentWeatherModel
import com.example.forecastapplication.model.data.ForecastList

sealed class WeatherState {

    object Loading : WeatherState()

    data class LoadCurrentInfo(
        val weather: CurrentWeatherModel
    ) : WeatherState()

    data class LoadFutureInfo(
        val weather: ForecastList
    ) : WeatherState()

    data class Error(
        val error: Throwable
    ) : WeatherState()

}