package com.example.forecastapplication.currentweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forecastapplication.currentweather.model.CurrentWeatherModel
import com.example.forecastapplication.core.repository.IRepository
import com.example.forecastapplication.currentweather.model.CurrentWeatherState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

interface ICurrentWeatherContract {
    val state: LiveData<CurrentWeatherState>
    fun fetchWeather(city: String)
}

class CurrentWeatherViewModel(
    private val issueRepository: IRepository
) : ViewModel(),
    ICurrentWeatherContract {

    override val state: MutableLiveData<CurrentWeatherState> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()
    private var fetchCurrencyDisposable: Disposable? = null

    init {
        fetchWeather("Odessa")
    }

    override fun fetchWeather(city: String) {
        fetchCurrencyDisposable?.dispose()

        val disposable = issueRepository
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
        compositeDisposable.add(disposable)
        fetchCurrencyDisposable = disposable
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}