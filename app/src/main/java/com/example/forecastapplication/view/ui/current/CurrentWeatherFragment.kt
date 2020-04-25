package com.example.forecastapplication.view.ui.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.forecastapplication.R
import com.example.forecastapplication.WeatherState
import com.example.forecastapplication.model.repository.IRepository
import com.example.forecastapplication.model.repository.RepositoryImpl
import com.example.forecastapplication.model.request.NetworkService
import kotlinx.android.synthetic.main.fragment_current_weather.*

class CurrentWeatherFragment : Fragment() {
    private lateinit var viewModel: ICurrentWeatherContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return modelClass.getConstructor(IRepository::class.java)
                    .newInstance(RepositoryImpl(NetworkService().getApiService()))
            }
        }).get(CurrentWeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_current_weather, container, false)

        viewModel.fetchWeather("Moscow")

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                WeatherState.Loading -> {
                    textViewTemperatureCurrent.visibility = View.GONE
                    textViewConditionCurrent.visibility = View.GONE
                    textViewMaxCurrent.visibility = View.GONE
                    textViewMinCurrent.visibility = View.GONE
                    textViewWindCurrent.visibility = View.GONE
                    textViewPrecipitationCurrent.visibility = View.GONE
                    textViewVisibilityCurrent.visibility = View.GONE
                    progressBarLoadingCurrent.visibility = View.VISIBLE
                    textViewLoadingCurrent.visibility = View.VISIBLE
                }
                is WeatherState.LoadCurrentInfo -> {
                    textViewTemperatureCurrent.visibility = View.VISIBLE
                    textViewConditionCurrent.visibility = View.VISIBLE
                    textViewMaxCurrent.visibility = View.VISIBLE
                    textViewMinCurrent.visibility = View.VISIBLE
                    textViewWindCurrent.visibility = View.VISIBLE
                    textViewPrecipitationCurrent.visibility = View.VISIBLE
                    textViewVisibilityCurrent.visibility = View.VISIBLE
                    progressBarLoadingCurrent.visibility = View.GONE
                    textViewLoadingCurrent.visibility = View.GONE

                    textViewTemperatureCurrent.text = state.weather.temp
                    textViewConditionCurrent.text = state.weather.status
                    textViewMaxCurrent.text = state.weather.maxTemp
                    textViewMinCurrent.text = state.weather.minTemp
                    textViewWindCurrent.text = state.weather.wind
                    textViewPrecipitationCurrent.text = state.weather.humidity
                    textViewVisibilityCurrent.text = state.weather.pressure
                }
                is WeatherState.LoadFutureInfo -> {
                }
                is WeatherState.Error -> {
                    textViewTemperatureCurrent.visibility = View.GONE
                    textViewConditionCurrent.visibility = View.GONE
                    textViewMaxCurrent.visibility = View.GONE
                    textViewMinCurrent.visibility = View.GONE
                    textViewWindCurrent.visibility = View.GONE
                    textViewPrecipitationCurrent.visibility = View.GONE
                    textViewVisibilityCurrent.visibility = View.GONE
                    progressBarLoadingCurrent.visibility = View.GONE
                    textViewLoadingCurrent.visibility = View.GONE
                }
            }

        })
        return view
    }
}

