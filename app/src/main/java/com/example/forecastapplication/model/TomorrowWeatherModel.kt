package com.example.forecastapplication.model

import com.example.forecastapplication.IViewObject

data class TomorrowWeatherModel(
    val status: String,
    val feelsLike: String,
    val temp: String,
    val wind: String,
    val humidity: String,
    val pressure: String,
    val image: String,
    val date: String
) : IViewObject