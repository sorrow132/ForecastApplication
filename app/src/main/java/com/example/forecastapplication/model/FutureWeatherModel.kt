package com.example.forecastapplication.model

data class FutureWeatherModel(
    val temp: String,
    val status: String,
    val icon: String,
    val date: String
): IViewObject