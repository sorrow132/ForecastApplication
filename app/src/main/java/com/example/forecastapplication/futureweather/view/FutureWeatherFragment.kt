package com.example.forecastapplication.futureweather.view

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
import com.example.forecastapplication.WeatherApplication
import com.example.forecastapplication.core.ITestRepository
import com.example.forecastapplication.futureweather.adapter.RecyclerViewAdapterForecast
import com.example.forecastapplication.core.repository.IRepository
import com.example.forecastapplication.futureweather.model.FutureWeatherState
import com.example.forecastapplication.futureweather.viewmodel.FutureWeatherViewModel
import com.example.forecastapplication.futureweather.viewmodel.IFutureWeatherViewModel
import kotlinx.android.synthetic.main.fragment_forecast_weather.*
import javax.inject.Inject

class FutureWeatherFragment : Fragment() {

    @Inject
    lateinit var secondRepository: ITestRepository

    @Inject
    lateinit var repository: IRepository

    private lateinit var viewModel: IFutureWeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as WeatherApplication).component.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return modelClass.getConstructor(
                    IRepository::class.java,
                    ITestRepository::class.java
                )
                    .newInstance(repository, secondRepository)
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
                FutureWeatherState.Loading -> {
                    textViewErrorFuture.visibility = View.GONE
                    buttonRetryFuture.visibility = View.GONE
                    recyclerViewForecast.visibility = View.GONE
                    progressBarLoadingForecast.visibility = View.VISIBLE
                    textViewLoadingFuture.visibility = View.VISIBLE
                    recycler.visibility = View.GONE
                }
                is FutureWeatherState.LoadFutureInfo -> {
                    textViewErrorFuture.visibility = View.GONE
                    buttonRetryFuture.visibility = View.GONE
                    recyclerViewForecast.visibility = View.GONE
                    progressBarLoadingForecast.visibility = View.GONE
                    textViewLoadingFuture.visibility = View.GONE
                    recycler.visibility = View.VISIBLE
                    recycler.adapter =
                        RecyclerViewAdapterForecast(state.forecastWeather)
                }
                is FutureWeatherState.Error -> {
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
