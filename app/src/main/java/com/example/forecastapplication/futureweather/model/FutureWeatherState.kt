package com.example.forecastapplication.futureweather.model

sealed class FutureWeatherState {

    object Loading : FutureWeatherState()

    data class LoadFutureInfo(
        val forecastWeather: List<FutureWeatherModel>
    ) : FutureWeatherState()

    data class Error(
        val error: Throwable
    ) : FutureWeatherState()

}