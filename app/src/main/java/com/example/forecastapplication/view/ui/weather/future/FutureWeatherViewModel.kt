package com.example.forecastapplication.view.ui.weather.future

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forecastapplication.WeatherState
import com.example.forecastapplication.model.FutureWeatherModel
import com.example.forecastapplication.model.repository.IRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class FutureWeatherViewModel(private val issueRepository: IRepository) : ViewModel(),
    IFutureWeatherViewModel {

    private val compositeDisposable = CompositeDisposable()
    private var fetchCurrencyDisposable: Disposable? = null

    override val state: MutableLiveData<WeatherState> = MutableLiveData()

    init {
        fetchInfo("Одесса")
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
                        entity.dateTxt.substring(0, 11)
                    )
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                state.value = WeatherState.Loading
            }.subscribe({
                state.value = WeatherState.LoadForecast(it)
            }, {
                state.value = WeatherState.Error(it)
            })
        compositeDisposable.add(disposable)
        fetchCurrencyDisposable = disposable
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}