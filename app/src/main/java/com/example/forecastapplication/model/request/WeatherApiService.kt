package com.example.forecastapplication.model.request

import com.example.forecastapplication.model.data.CurrentResponse

import com.example.forecastapplication.model.data.ForecastList
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    fun requestWeatherForCity(
        @Query("q") city: String, @Query("appid") apiKey: String, @Query("units") units: String
    ): Call<CurrentResponse>

    @GET("forecast")
    fun forecastWeatherForCity(
        @Query("q") city: String, @Query("appid") key: String, @Query("units") unit: String
    ): Call<ForecastList>

    //Rx
    @GET("weather")
    fun requestWeatherByCityRx(
        @Query("q") city: String, @Query("appid") apiKey: String, @Query("units") units: String
    ): Single<CurrentResponse>

    @GET("forecast")
    fun forecastWeatherByCityRx(
        @Query("q") city: String, @Query("appid") key: String, @Query("units") unit: String
    ): Single<ForecastList>
}

