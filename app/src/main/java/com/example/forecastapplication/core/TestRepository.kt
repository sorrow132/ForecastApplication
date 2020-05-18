package com.example.forecastapplication.core

import com.example.forecastapplication.core.db.CityEntity
import com.example.forecastapplication.core.repository.IDBRepository
import com.example.forecastapplication.currentweather.viewmodel.MySealedClass
import io.reactivex.subjects.BehaviorSubject

interface ITestRepository {

    val myBehavior: BehaviorSubject<MySealedClass>

    fun test(cityEntity: CityEntity)
}

class TestRepository(private val db: IDBRepository) : ITestRepository {

    override val myBehavior: BehaviorSubject<MySealedClass> =
        BehaviorSubject.createDefault(MySealedClass.NonExist)

    override fun test(cityEntity: CityEntity) {
        myBehavior.onNext(MySealedClass.Exist(db.getSelectedCity()))
    }
}