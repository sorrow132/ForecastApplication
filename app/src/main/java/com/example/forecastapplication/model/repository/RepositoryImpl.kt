package com.example.forecastapplication.model.repository

import com.example.forecastapplication.model.data.CurrentResponse
import com.example.forecastapplication.model.data.ForecastList
import com.example.forecastapplication.model.request.WeatherApiService
import io.reactivex.Single

class RepositoryImpl(private val networkService: WeatherApiService) : IRepository {
    override fun getCurrentWeatherInfo(city: String): Single<CurrentResponse> {
        return networkService.requestWeatherByCityRx(
            city,
            "22d417de39746d69715379f3d114e766",
            "metric"
        )
    }

    override fun getFutureWeatherInfo(city: String): Single<ForecastList> {
        return networkService.forecastWeatherByCityRx(
            city,
            "22d417de39746d69715379f3d114e766",
            "metric"
        )
    }
}