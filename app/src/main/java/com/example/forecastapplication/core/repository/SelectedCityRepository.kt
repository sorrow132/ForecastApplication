package com.example.forecastapplication.core.repository

import com.example.forecastapplication.core.db.CityEntity
import com.example.forecastapplication.cities.model.ChosenCityState
import io.reactivex.subjects.BehaviorSubject

interface ITestRepository {

    val myBehavior: BehaviorSubject<ChosenCityState>

    fun setSelectedCity(cityEntity: CityEntity)
}

class TestRepository(private val db: IDBRepository) :
    ITestRepository {

    override val myBehavior: BehaviorSubject<ChosenCityState> =
        BehaviorSubject.createDefault(ChosenCityState.NonExist)

    override fun setSelectedCity(cityEntity: CityEntity) {
        myBehavior.onNext(ChosenCityState.Exist(db.getSelectedCity()))
    }
}