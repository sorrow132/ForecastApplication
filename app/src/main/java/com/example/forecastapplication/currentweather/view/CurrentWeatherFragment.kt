package com.example.forecastapplication.currentweather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.forecastapplication.R
import com.example.forecastapplication.WeatherApplication
import com.example.forecastapplication.core.repository.ITestRepository
import com.example.forecastapplication.core.repository.IRepository
import com.example.forecastapplication.currentweather.model.CurrentWeatherState
import com.example.forecastapplication.currentweather.viewmodel.CurrentWeatherViewModel
import com.example.forecastapplication.currentweather.viewmodel.ICurrentWeatherContract
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_current_weather.*
import javax.inject.Inject

class CurrentWeatherFragment : Fragment() {

    @Inject
    lateinit var repository: IRepository

    @Inject
    lateinit var secondRepository: ITestRepository

    private lateinit var viewModel: ICurrentWeatherContract

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as WeatherApplication).component.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return modelClass.getConstructor(
                    IRepository::class.java, ITestRepository::class.java
                )
                    .newInstance(repository, secondRepository)
            }
        }).get(CurrentWeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_current_weather, container, false)

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                CurrentWeatherState.Loading -> {
                    textViewTemperatureCurrent.visibility = View.GONE
                    textViewConditionCurrent.visibility = View.GONE
                    textViewMaxCurrent.visibility = View.GONE
                    textViewMinCurrent.visibility = View.GONE
                    textViewWindCurrent.visibility = View.GONE
                    textViewPrecipitationCurrent.visibility = View.GONE
                    textViewVisibilityCurrent.visibility = View.GONE
                    textViewErrorCurrent.visibility = View.GONE
                    buttonRetryCurrent.visibility = View.GONE
                    progressBarLoadingCurrent.visibility = View.VISIBLE
                    textViewLoadingCurrent.visibility = View.VISIBLE
                }
                is CurrentWeatherState.LoadInfo -> {
                    textViewTemperatureCurrent.visibility = View.VISIBLE
                    textViewConditionCurrent.visibility = View.VISIBLE
                    textViewMaxCurrent.visibility = View.VISIBLE
                    textViewMinCurrent.visibility = View.VISIBLE
                    textViewWindCurrent.visibility = View.VISIBLE
                    textViewPrecipitationCurrent.visibility = View.VISIBLE
                    textViewVisibilityCurrent.visibility = View.VISIBLE
                    textViewErrorCurrent.visibility = View.GONE
                    buttonRetryCurrent.visibility = View.GONE
                    progressBarLoadingCurrent.visibility = View.GONE
                    textViewLoadingCurrent.visibility = View.GONE

                    textViewTemperatureCurrent.text = state.weather.temp
                    textViewConditionCurrent.text = state.weather.status
                    textViewMaxCurrent.text = state.weather.maxTemp
                    textViewMinCurrent.text = state.weather.minTemp
                    textViewWindCurrent.text = state.weather.wind
                    textViewPrecipitationCurrent.text = state.weather.humidity
                    textViewVisibilityCurrent.text = state.weather.pressure
                    Picasso.get().load(state.weather.image).into(imageViewCurrent)

                }
                is CurrentWeatherState.Error -> {
                    buttonRetryCurrent.visibility = View.VISIBLE
                    textViewErrorCurrent.visibility = View.VISIBLE
                    textViewTemperatureCurrent.visibility = View.GONE
                    textViewConditionCurrent.visibility = View.GONE
                    textViewMaxCurrent.visibility = View.GONE
                    textViewMinCurrent.visibility = View.GONE
                    textViewWindCurrent.visibility = View.GONE
                    textViewPrecipitationCurrent.visibility = View.GONE
                    textViewVisibilityCurrent.visibility = View.GONE
                    progressBarLoadingCurrent.visibility = View.GONE
                    textViewLoadingCurrent.visibility = View.GONE
                    textViewErrorCurrent.text = state.error.message
                }
            }
        })
        return view
    }
}

