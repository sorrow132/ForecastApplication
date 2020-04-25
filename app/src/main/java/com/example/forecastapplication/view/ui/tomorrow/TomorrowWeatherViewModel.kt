package com.example.forecastapplication.view.ui.tomorrow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.forecastapplication.WeatherState
import com.example.forecastapplication.model.TomorrowWeatherModel
import com.example.forecastapplication.model.repository.IRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TomorrowWeatherViewModel(private val issueRepository: IRepository) : ViewModel(),
    ITomorrowWeatherContract {

    private val compositeDisposable = CompositeDisposable()
    private var fetchCurrencyDisposable: Disposable? = null

    override val state: MutableLiveData<WeatherState> = MutableLiveData()

    override fun fetchInfo(city: String) {
        fetchCurrencyDisposable?.dispose()

        val disposable = issueRepository
            .getFutureWeatherInfo(city)
            .map { response ->

                TomorrowWeatherModel(
                    response.weatherList[0].weather[0].main,
                    response.weatherList[0].main.feelsLike.toString(),
                    response.weatherList[0].main.temp.toString(),
                    response.weatherList[0].wind.speed.toString(),
                    response.weatherList[0].main.humidity.toString(),
                    response.weatherList[0].main.pressure.toString(),
                    response.weatherList[0].weather[0].icon,
                    response.weatherList[0].dateTxt
                )

            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                state.value = WeatherState.Loading
            }.subscribe({
                state.value = WeatherState.LoadFutureInfo(it)
            })
            {
                state.value = WeatherState.Error(it)
            }
        compositeDisposable.add(disposable)
        fetchCurrencyDisposable = disposable
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }
}