package com.example.forecastapplication.view.ui.weather.tomorrow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.R
import com.example.forecastapplication.WeatherState
import com.example.forecastapplication.model.repository.IRepository
import com.example.forecastapplication.model.repository.RepositoryImpl
import com.example.forecastapplication.model.request.NetworkService
import com.example.forecastapplication.view.ui.adapters.RecyclerViewAdapterTomorrow
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_tomorrow_weather.*

class TomorrowWeatherFragment : Fragment() {

    private lateinit var viewModel: TomorrowWeatherViewModel

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

        val recycler: RecyclerView = view.findViewById(R.id.recyclerViewTomorrow)

        recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                WeatherState.Loading -> {
                    textViewConditionTomorrow.visibility = View.GONE
                    textViewTemperatureTomorrow.visibility = View.GONE
                    textViewFeelsLikeTomorrow.visibility = View.GONE
                    imageViewConditionTomorrow.visibility = View.GONE
                    textViewWindTomorrow.visibility = View.GONE
                    textViewPrecipitationTomorrow.visibility = View.GONE
                    textViewVisibilityTomorrow.visibility = View.GONE
                    recyclerViewTomorrow.visibility = View.GONE
                    textViewErrorTomorrow.visibility = View.GONE
                    buttonRetryTomorrow.visibility = View.GONE
                    progressBarLoadingTomorrow.visibility = View.VISIBLE
                    textViewLoadingTomorrow.visibility = View.VISIBLE
                }
                is WeatherState.LoadTomorrowInfo -> {
                    textViewConditionTomorrow.visibility = View.VISIBLE
                    textViewTemperatureTomorrow.visibility = View.VISIBLE
                    textViewFeelsLikeTomorrow.visibility = View.VISIBLE
                    imageViewConditionTomorrow.visibility = View.VISIBLE
                    textViewWindTomorrow.visibility = View.VISIBLE
                    textViewPrecipitationTomorrow.visibility = View.VISIBLE
                    textViewVisibilityTomorrow.visibility = View.VISIBLE
                    recyclerViewTomorrow.visibility = View.VISIBLE
                    textViewErrorTomorrow.visibility = View.GONE
                    buttonRetryTomorrow.visibility = View.GONE
                    progressBarLoadingTomorrow.visibility = View.GONE
                    textViewLoadingTomorrow.visibility = View.GONE

                    recycler.adapter = RecyclerViewAdapterTomorrow(state.weather)
                    Picasso.get().load(state.weather[2].image).into(imageViewConditionTomorrow)
                    textViewConditionTomorrow.text = state.weather[2].status
                    textViewTemperatureTomorrow.text = state.weather[2].temp
                    textViewFeelsLikeTomorrow.text = state.weather[2].feelsLike
                    textViewWindTomorrow.text = state.weather[2].wind
                    textViewPrecipitationTomorrow.text = state.weather[2].pressure
                    textViewVisibilityTomorrow.text = state.weather[2].humidity
                }
                is WeatherState.Error -> {
                    textViewErrorTomorrow.visibility = View.VISIBLE
                    buttonRetryTomorrow.visibility = View.VISIBLE
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
