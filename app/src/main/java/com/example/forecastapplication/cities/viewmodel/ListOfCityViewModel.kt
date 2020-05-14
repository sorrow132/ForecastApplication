package com.example.forecastapplication.cities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forecastapplication.cities.model.CityModel
import com.example.forecastapplication.core.db.CityEntity
import com.example.forecastapplication.core.repository.IDBRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface IListOfCityViewModelContract {
    val cityList: LiveData<List<CityEntity>>
    fun addNewCity(city: CityEntity)
    fun deleteCity(city: CityEntity)
}

class ListOfCityViewModel(private val issueDatabase: IDBRepository) : ViewModel(),
    IListOfCityViewModelContract {

    override val cityList: MutableLiveData<List<CityEntity>> = MutableLiveData()

    init {
//        issueDatabase.getAllCities()
//            .map {
//                CityModel(
//
//                )
//            }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { listOfCity -> cityList.value = listOfCity }
//            .dispose()
    }

    override fun addNewCity(city: CityEntity) {

    }

    override fun deleteCity(city: CityEntity) {

    }

}