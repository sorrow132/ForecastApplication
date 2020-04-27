package com.example.forecastapplication.view.ui.weather.future

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
import com.example.forecastapplication.view.ui.adapters.RecyclerViewAdapterForecast
import kotlinx.android.synthetic.main.fragment_forecast_weather.*

class FutureWeatherFragment : Fragment() {

    private lateinit var viewModel: IFutureWeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return modelClass.getConstructor(IRepository::class.java)
                    .newInstance(RepositoryImpl(NetworkService().getApiService()))
            }
        }).get(FutureWeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forecast_weather, container, false)

        val recycler: RecyclerView = view.findViewById(R.id.recyclerViewForecast)

        recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                WeatherState.Loading -> {
                    textViewErrorFuture.visibility = View.GONE
                    buttonRetryFuture.visibility = View.GONE
                    recyclerViewForecast.visibility = View.GONE
                    progressBarLoadingForecast.visibility = View.VISIBLE
                    textViewLoadingFuture.visibility = View.VISIBLE
                    recycler.visibility = View.GONE
                }
                is WeatherState.LoadForecast -> {
                    textViewErrorFuture.visibility = View.GONE
                    buttonRetryFuture.visibility = View.GONE
                    recyclerViewForecast.visibility = View.GONE
                    progressBarLoadingForecast.visibility = View.GONE
                    textViewLoadingFuture.visibility = View.GONE
                    recycler.visibility = View.VISIBLE
                    recycler.adapter =
                        RecyclerViewAdapterForecast(state.forecastWeather)
                }
                is WeatherState.Error -> {
                    textViewErrorFuture.visibility = View.VISIBLE
                    buttonRetryFuture.visibility = View.VISIBLE
                    recyclerViewForecast.visibility = View.GONE
                    progressBarLoadingForecast.visibility = View.GONE
                    textViewLoadingFuture.visibility = View.GONE
                    recycler.visibility = View.GONE
                }
            }
        })
        return view
    }

}
