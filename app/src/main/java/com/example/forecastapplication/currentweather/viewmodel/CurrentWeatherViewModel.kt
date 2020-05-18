package com.example.forecastapplication.currentweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.forecastapplication.core.BaseViewModel
import com.example.forecastapplication.core.ITestRepository
import com.example.forecastapplication.core.repository.IDBRepository
import com.example.forecastapplication.core.repository.IRepository
import com.example.forecastapplication.core.request.model.CurrentResponse
import com.example.forecastapplication.currentweather.model.CurrentWeatherModel
import com.example.forecastapplication.currentweather.model.CurrentWeatherState
import com.example.forecastapplication.utils.addTo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

interface ICurrentWeatherContract {
    val state: LiveData<CurrentWeatherState>
    fun fetchWeather(city: String)
}

class CurrentWeatherViewModel(
    private val issueRepository: IRepository,
    secondRepository: ITestRepository
) : BaseViewModel(),
    ICurrentWeatherContract {

    override val state: MutableLiveData<CurrentWeatherState> = MutableLiveData()

    private var fetchCurrencyDisposable: Disposable? = null

    init {
        secondRepository
            .myBehavior
            .distinctUntilChanged()
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it) {
                    is MySealedClass.Exist -> {
                        fetchWeather(it.myString.city)
                    }
                    MySealedClass.NonExist -> {
                        Observable.empty<CurrentResponse>()
                    }
                }
            }
            .addTo(compositeDisposable)
    }

    override fun fetchWeather(city: String) {
        fetchCurrencyDisposable?.dispose()

        issueRepository
            .getCurrentWeatherInfo(city)
            .map { response ->
                CurrentWeatherModel(
                    response.weather[0].main,
                    "Max: " + response.main.tempMax.toString() + "°C ",
                    "Min: " + response.main.tempMin.toString() + "°C",
                    response.main.temp.toString() + "°C",
                    "Wind: SE, " + response.wind.speed.toString() + " m/s",
                    "Humidity: " + response.main.humidity.toString() + " %",
                    "Pressure: " + response.main.pressure.toString(),
                    "https://openweathermap.org/img/wn/${response.weather[0].icon}.png"
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                state.value = CurrentWeatherState.Loading
            }
            .subscribe({
                state.value = CurrentWeatherState.LoadInfo(it)
            }, {
                state.value = CurrentWeatherState.Error(it)
            })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}