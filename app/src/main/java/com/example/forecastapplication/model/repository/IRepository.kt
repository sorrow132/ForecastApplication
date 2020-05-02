package com.example.forecastapplication.model.repository

import com.example.forecastapplication.model.data.CurrentResponse
import com.example.forecastapplication.model.data.ForecastList
import io.reactivex.Single

interface IRepository {

    fun getFirstWeatherInfo(lat: Double, lon: Double): Single<ForecastList>

    fun getCurrentWeatherInfo(city: String): Single<CurrentResponse>

    fun getFutureWeatherInfo(city: String): Single<ForecastList>
}