package com.example.forecastapplication.model.request

import com.example.forecastapplication.model.data.CurrentResponse

import com.example.forecastapplication.model.data.ForecastList
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    //    first time open app (lat, lon )
    //    api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={your api key}
    @GET("weather")
    fun firstWeatherRequest(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Single<ForecastList>


    //    Not the first authorization
    @GET("weather")
    fun requestWeatherByCityRx(
        @Query("q") city: String, @Query("appid") apiKey: String, @Query("units") units: String
    ): Single<CurrentResponse>

    @GET("forecast")
    fun forecastWeatherByCityRx(
        @Query("q") city: String, @Query("appid") key: String, @Query("units") unit: String
    ): Single<ForecastList>
}

