package com.example.forecastapplication.model

data class CurrentWeatherModel(
    val status: String,
    val maxTemp: String,
    val minTemp: String,
    val temp: String,
    val wind: String,
    val humidity: String,
    val pressure: String,
    val image: String
)