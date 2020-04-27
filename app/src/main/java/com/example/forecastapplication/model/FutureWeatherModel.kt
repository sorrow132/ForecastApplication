package com.example.forecastapplication.model

import com.example.forecastapplication.IViewObject

data class FutureWeatherModel(
    val temp: String,
    val status: String,
    val icon: String,
    val date: String
): IViewObject