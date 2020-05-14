package com.example.forecastapplication.core.request

import com.example.forecastapplication.core.request.model.CurrentResponse

import com.example.forecastapplication.core.request.model.ForecastList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    fun requestWeatherByCityRx(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Single<CurrentResponse>

    @GET("forecast")
    fun forecastWeatherByCityRx(
        @Query("q") city: String,
        @Query("appid") key: String,
        @Query("units") unit: String
    ): Single<ForecastList>
}

