package com.example.forecastapplication.core.db

import com.example.forecastapplication.core.repository.IDBRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class DatabaseImpl(private val dao: CityDao) :
    IDBRepository {

    override fun getAllCities(): Observable<List<CityEntity>> {
        return dao.getCity()
    }

    override fun insertCity(city: CityEntity): Completable {
        return dao.insert(city)
    }

    override fun deleteCity(city: CityEntity): Completable {
        return dao.deleteCity(city)
    }

    override fun update(city: CityEntity) {
         dao.update(city)
    }

    override fun getSelectedCity() {
        dao.getSelectedCity()
    }

    override fun updateSelectedCity(city: CityEntity) {
        dao.updateSelectedCity(city)
    }


}

