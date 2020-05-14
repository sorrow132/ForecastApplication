package com.example.forecastapplication.tomorrowweather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forecastapplication.core.repository.IRepository
import com.example.forecastapplication.tomorrowweather.model.TomorrowWeatherModel
import com.example.forecastapplication.tomorrowweather.model.TomorrowWeatherState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

interface ITomorrowWeatherContract {
    val state: LiveData<TomorrowWeatherState>
    fun fetchInfo(city: String)
}

class TomorrowWeatherViewModel(private val issueRepository: IRepository) : ViewModel(),
    ITomorrowWeatherContract {

    private val compositeDisposable = CompositeDisposable()
    private var fetchCurrencyDisposable: Disposable? = null

    override val state: MutableLiveData<TomorrowWeatherState> = MutableLiveData()

    override fun fetchInfo(city: String) {
        fetchCurrencyDisposable?.dispose()

        val disposable = issueRepository
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
        compositeDisposable.add(disposable)
        fetchCurrencyDisposable = disposable
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}