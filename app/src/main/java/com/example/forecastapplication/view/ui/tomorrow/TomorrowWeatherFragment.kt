package com.example.forecastapplication.view.ui.tomorrow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.forecastapplication.R
import com.example.forecastapplication.WeatherState
import com.example.forecastapplication.model.repository.IRepository
import com.example.forecastapplication.model.repository.RepositoryImpl
import com.example.forecastapplication.model.request.NetworkService
import kotlinx.android.synthetic.main.fragment_tomorrow_weather.*

class TomorrowWeatherFragment : Fragment() {
    private lateinit var viewModel: TomorrowWeatherViewModel
    private lateinit var testTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return modelClass.getConstructor(IRepository::class.java)
                    .newInstance(RepositoryImpl(NetworkService().getApiService()))
            }
        }).get(TomorrowWeatherViewModel::class.java)

        viewModel.fetchInfo("Moscow")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tomorrow_weather, container, false)

        testTextView = view.findViewById(R.id.textViewTemperatureTomorrow)


        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                WeatherState.Loading -> {
//                    textViewConditionTomorrow.visibility = View.GONE
//                    textViewTemperatureTomorrow.visibility = View.GONE
//                    textViewFeelsLikeTomorrow.visibility = View.GONE
//                    imageViewConditionTomorrow.visibility = View.GONE
//                    textViewWindTomorrow.visibility = View.GONE
//                    textViewPrecipitationTomorrow.visibility = View.GONE
//                    textViewVisibilityTomorrow.visibility = View.GONE
//                    recyclerViewTomorrow.visibility = View.GONE
//                    progressBarLoadingTomorrow.visibility = View.VISIBLE
//                    textViewLoadingTomorrow.visibility = View.VISIBLE
                }
                is WeatherState.LoadFutureInfo -> {
                        testTextView.text = state.weather.temp
//                    textViewConditionTomorrow.visibility = View.VISIBLE
//                    textViewTemperatureTomorrow.visibility = View.VISIBLE
//                    textViewFeelsLikeTomorrow.visibility = View.VISIBLE
//                    imageViewConditionTomorrow.visibility = View.VISIBLE
//                    textViewWindTomorrow.visibility = View.VISIBLE
//                    textViewPrecipitationTomorrow.visibility = View.VISIBLE
//                    textViewVisibilityTomorrow.visibility = View.VISIBLE
//                    recyclerViewTomorrow.visibility = View.VISIBLE
//                    progressBarLoadingTomorrow.visibility = View.GONE
//                    textViewLoadingTomorrow.visibility = View.GONE

                    textViewConditionTomorrow.text = state.weather.status
                    textViewTemperatureTomorrow.text = state.weather.temp
                    textViewFeelsLikeTomorrow.text = state.weather.feelsLike
                    imageViewConditionTomorrow
                    textViewWindTomorrow.text = state.weather.wind
                    textViewPrecipitationTomorrow.text = state.weather.pressure
                    textViewVisibilityTomorrow.text = state.weather.humidity

                }
                is WeatherState.Error -> {
                    textViewConditionTomorrow.visibility = View.GONE
                    textViewTemperatureTomorrow.visibility = View.GONE
                    textViewFeelsLikeTomorrow.visibility = View.GONE
                    imageViewConditionTomorrow.visibility = View.GONE
                    textViewWindTomorrow.visibility = View.GONE
                    textViewPrecipitationTomorrow.visibility = View.GONE
                    textViewVisibilityTomorrow.visibility = View.GONE
                    recyclerViewTomorrow.visibility = View.GONE
                    progressBarLoadingTomorrow.visibility = View.GONE
                    textViewLoadingTomorrow.visibility = View.GONE
                }
            }
        })

        return view
    }
}
