package com.example.forecastapplication.core.repository

import com.example.forecastapplication.core.db.CityEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface IDBRepository {
    fun getAllCities(): Observable<List<CityEntity>>
    fun insertCity(city: CityEntity): Completable
    fun deleteCity(city: CityEntity): Completable

}