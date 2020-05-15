package com.example.forecastapplication.cities.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forecastapplication.cities.model.CitiesState
import com.example.forecastapplication.cities.model.CityModel
import com.example.forecastapplication.core.db.CityEntity
import com.example.forecastapplication.core.repository.IDBRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

interface IListOfCityViewModelContract {

    val state: LiveData<CitiesState>
    val cityList: LiveData<List<CityModel>>
    fun addNewCity(city: CityEntity)
    fun deleteCity(city: CityEntity)
}

class ListOfCityViewModel(private val cityDatabase: IDBRepository) : ViewModel(),
    IListOfCityViewModelContract {

    override val state: MutableLiveData<CitiesState> = MutableLiveData()
    override val cityList: MutableLiveData<List<CityModel>> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()
    private var fetchCurrencyDisposable: Disposable? = null

    init {
        val test = CityEntity("Odessa")

        cityDatabase.insertCity(test)

        cityDatabase.getAllCities()

        val disposable = cityDatabase
            .getAllCities()
            .map { listOfCity ->
                listOfCity.map {
                    CityModel(city = it.city)
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
        compositeDisposable.add(disposable)
        fetchCurrencyDisposable = disposable
    }

    override fun addNewCity(city: CityEntity) {
        Completable
            .fromAction {
                cityDatabase.insertCity(city)
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun deleteCity(city: CityEntity) {
        Completable.fromAction {
            cityDatabase.deleteCity(city)
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}