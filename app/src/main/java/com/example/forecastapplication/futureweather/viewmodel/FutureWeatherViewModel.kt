package com.example.forecastapplication.futureweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.forecastapplication.core.BaseViewModel
import com.example.forecastapplication.futureweather.model.FutureWeatherModel
import com.example.forecastapplication.core.repository.IRepository
import com.example.forecastapplication.futureweather.model.FutureWeatherState
import com.example.forecastapplication.utils.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

interface IFutureWeatherViewModel {
    val state: LiveData<FutureWeatherState>
    fun fetchInfo(city: String)
}

class FutureWeatherViewModel(private val issueRepository: IRepository) :
    BaseViewModel(),
    IFutureWeatherViewModel {

    private var fetchCurrencyDisposable: Disposable? = null

    override val state: MutableLiveData<FutureWeatherState> = MutableLiveData()

    init {
        fetchInfo("Odessa")
    }

    override fun fetchInfo(city: String) {
        fetchCurrencyDisposable?.dispose()
        issueRepository
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
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}