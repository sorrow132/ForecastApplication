package com.example.forecastapplication.futureweather.model

import com.example.forecastapplication.core.IViewObject

data class FutureWeatherModel(
    val temp: String,
    val status: String,
    val icon: String,
    val date: String,
    val hour: String
) : IViewObject