package com.example.forecastapplication.futureweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forecastapplication.futureweather.model.FutureWeatherModel
import com.example.forecastapplication.core.repository.IRepository
import com.example.forecastapplication.futureweather.model.FutureWeatherState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface IFutureWeatherViewModel {
    val state: LiveData<FutureWeatherState>
    fun fetchInfo(city: String)
}

class FutureWeatherViewModel @Inject constructor(private val issueRepository: IRepository) :
    ViewModel(),
    IFutureWeatherViewModel {

    private val compositeDisposable = CompositeDisposable()
    private var fetchCurrencyDisposable: Disposable? = null

    override val state: MutableLiveData<FutureWeatherState> = MutableLiveData()

    init {
        fetchInfo("Odessa")
    }

    override fun fetchInfo(city: String) {
        val disposable = issueRepository
            .getFutureWeatherInfo(city)
            .map { list ->
                list.weatherList.map { entity ->
                    FutureWeatherModel(
                        entity.main.temp.toString() + "°C ",
                        entity.weather[0].main,
                        "https://openweathermap.org/img/wn/${entity.weather[0].icon}.png",
                        entity.dateTxt.substring(0, 11),
                        entity.dateTxt.substring(11, 16)
                    )
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                state.value = FutureWeatherState.Loading
            }.subscribe({
                state.value = FutureWeatherState.LoadFutureInfo(it)
            }, {
                state.value = FutureWeatherState.Error(it)
            })
        compositeDisposable.add(disposable)
        fetchCurrencyDisposable = disposable
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}