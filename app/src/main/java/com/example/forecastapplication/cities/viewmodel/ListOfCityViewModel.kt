package com.example.forecastapplication.cities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.forecastapplication.core.BaseViewModel
import com.example.forecastapplication.cities.model.CitiesState
import com.example.forecastapplication.cities.model.CityModel
import com.example.forecastapplication.core.db.CityEntity
import com.example.forecastapplication.core.repository.IDBRepository
import com.example.forecastapplication.utils.addTo
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface IListOfCityViewModelContract {

    val state: LiveData<CitiesState>

    val cityList: LiveData<List<CityModel>>

    fun addNewCity(city: String)

    fun deleteCity(city: CityEntity)

    fun selectCity(city: CityEntity)

    fun changeStateToAddNew()

    fun changeToDefaultState()
}

class ListOfCityViewModel(private val cityDatabase: IDBRepository) : BaseViewModel(),
    IListOfCityViewModelContract {

    override val state: MutableLiveData<CitiesState> = MutableLiveData()
    override val cityList: MutableLiveData<List<CityModel>> = MutableLiveData()

    init {
        initInfoFromDb()
    }

    private fun initInfoFromDb() {
        cityDatabase
            .getAllCities()
            .map { listOfCity ->
                listOfCity.map {
                    CityModel(entity = it)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                state.value = CitiesState.Loading
            }
            .subscribe({
                if (it.isEmpty()) {
                    state.value = CitiesState.EmptyInfo
                } else {
                    state.value = CitiesState.LoadInfo(it)
                }
            }, {
                state.value = CitiesState.Error(it)
            })
            .addTo(compositeDisposable)
    }

    override fun addNewCity(city: String) {
        val cityEntity = CityEntity(city, 0)
        cityDatabase
            .insertCity(cityEntity)
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    override fun deleteCity(city: CityEntity) {
        cityDatabase
            .deleteCity(city)
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    override fun selectCity(city: CityEntity) {
        Completable
            .fromAction {
                cityDatabase.updateSelectedCity(city)
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    override fun changeStateToAddNew() {
        state.postValue(CitiesState.AddNew)
    }

    override fun changeToDefaultState() {
        initInfoFromDb()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}