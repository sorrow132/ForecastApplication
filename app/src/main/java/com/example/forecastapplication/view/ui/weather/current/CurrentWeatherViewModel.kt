package com.example.forecastapplication.view.ui.weather.current

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forecastapplication.WeatherApplication
import com.example.forecastapplication.WeatherState
import com.example.forecastapplication.model.CurrentWeatherModel
import com.example.forecastapplication.model.db.BaseLocationDao
import com.example.forecastapplication.model.db.CitiesDataBase
import com.example.forecastapplication.model.db.CitiesEntity
import com.example.forecastapplication.model.repository.IRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CurrentWeatherViewModel(private val issueRepository: IRepository) : ViewModel(),
    ICurrentWeatherContract {

    override val state: MutableLiveData<WeatherState> = MutableLiveData()

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
                state.value = WeatherState.Loading
            }
            .subscribe({
                state.value = WeatherState.LoadCurrentInfo(it)
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