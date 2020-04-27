package com.example.forecastapplication

import com.example.forecastapplication.model.CurrentWeatherModel
import com.example.forecastapplication.model.FutureWeatherModel
import com.example.forecastapplication.model.TomorrowWeatherModel

sealed class WeatherState {

    object Loading : WeatherState()

    data class LoadCurrentInfo(
        val weather: CurrentWeatherModel
    ) : WeatherState()

    data class LoadTomorrowInfo(
        val weather: List<TomorrowWeatherModel>
    ) : WeatherState()

    data class LoadForecast(
        val forecastWeather: List<FutureWeatherModel>
    ) : WeatherState()

    data class Error(
        val error: Throwable
    ) : WeatherState()

}