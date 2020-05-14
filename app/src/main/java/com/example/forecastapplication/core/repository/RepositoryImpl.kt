package com.example.forecastapplication.core.repository

import com.example.forecastapplication.core.repository.IDBRepository
import com.example.forecastapplication.core.repository.IRepository
import com.example.forecastapplication.core.db.CityEntity
import com.example.forecastapplication.core.request.WeatherApiService
import com.example.forecastapplication.core.request.model.CurrentResponse
import com.example.forecastapplication.core.request.model.ForecastList
import com.example.forecastapplication.utils.API_KEY
import com.example.forecastapplication.utils.UNIT
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class RepositoryImpl(
    private val networkService: WeatherApiService,
    private val dbRepository: IDBRepository
) : IRepository, IDBRepository {

    override fun getAllCities(): Observable<List<CityEntity>> {
        return dbRepository.getAllCities()
    }

    override fun insertCity(city: CityEntity): Completable {
        return dbRepository.insertCity(city)
    }

    override fun deleteCity(city: CityEntity): Completable {
        return dbRepository.deleteCity(city)
    }

    override fun getCurrentWeatherInfo(city: String): Single<CurrentResponse> {
        return networkService.requestWeatherByCityRx(city, API_KEY, UNIT)
    }

    override fun getFutureWeatherInfo(city: String): Single<ForecastList> {
        return networkService.forecastWeatherByCityRx(city, API_KEY, UNIT)
    }
}