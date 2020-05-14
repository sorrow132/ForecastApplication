package com.example.forecastapplication.core.repository

import com.example.forecastapplication.core.request.model.CurrentResponse
import com.example.forecastapplication.core.request.model.ForecastList
import com.example.forecastapplication.core.request.WeatherApiService
import io.reactivex.Single

interface IRepository {
    fun getCurrentWeatherInfo(city: String): Single<CurrentResponse>
    fun getFutureWeatherInfo(city: String): Single<ForecastList>
}