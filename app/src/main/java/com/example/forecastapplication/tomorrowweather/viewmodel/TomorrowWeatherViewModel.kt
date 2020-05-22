package com.example.forecastapplication.tomorrowweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.forecastapplication.core.BaseViewModel
import com.example.forecastapplication.core.repository.ITestRepository
import com.example.forecastapplication.core.repository.IRepository
import com.example.forecastapplication.core.request.model.CurrentResponse
import com.example.forecastapplication.cities.model.ChosenCityState
import com.example.forecastapplication.tomorrowweather.model.TomorrowWeatherModel
import com.example.forecastapplication.tomorrowweather.model.TomorrowWeatherState
import com.example.forecastapplication.utils.addTo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

interface ITomorrowWeatherContract {
    val state: LiveData<TomorrowWeatherState>
    fun fetchInfo(city: String)
}

class TomorrowWeatherViewModel(
    private val issueRepository: IRepository,
    secondRepository: ITestRepository
) : BaseViewModel(),
    ITomorrowWeatherContract {

    private var fetchCurrencyDisposable: Disposable? = null

    override val state: MutableLiveData<TomorrowWeatherState> = MutableLiveData()

    init {
        secondRepository
            .myBehavior
            .distinctUntilChanged()
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is ChosenCityState.Exist -> {
                        fetchInfo(it.city.city)
                    }
                    ChosenCityState.NonExist -> {
                        Observable.empty<CurrentResponse>()
                    }
                }
            }
            .addTo(compositeDisposable)
    }

    override fun fetchInfo(city: String) {
        fetchCurrencyDisposable?.dispose()
        issueRepository
            .getFutureWeatherInfo(city)
            .map { list ->

                list.weatherList.map { entity ->
                    TomorrowWeatherModel(
                        entity.weather[0].main,
                        "Feels like: " + entity.main.feelsLike.toString() + "°C ",
                        entity.main.tempMax.toString() + "°C ",
                        "Wind: SE, " + entity.wind.speed.toString() + " m/s",
                        "Humidity: " + entity.main.humidity.toString() + " %",
                        "Pressure: " + entity.main.pressure.toString(),
                        "https://openweathermap.org/img/wn/${entity.weather[0].icon}.png",
                        entity.dateTxt.substring(11, 16)
                    )
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                state.value =
                    TomorrowWeatherState.Loading
            }.subscribe({
                state.value =
                    TomorrowWeatherState.LoadTomorrowInfo(it)
            })
            {
                state.value =
                    TomorrowWeatherState.Error(it)
            }
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}