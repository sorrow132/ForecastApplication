package com.example.forecastapplication.view.ui.future

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.R

class ForecastWeatherFragment(): Fragment(){
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forecast_weather, container, false)
//        recyclerView = view.findViewById(R.id.recyclerView)
//
//        recyclerView.layoutManager =
//            LinearLayoutManager(context, VERTICAL, false)
//        recyclerView.addItemDecoration(object :
//            DividerItemDecoration(context, LinearLayoutManager.VERTICAL) {})


//        (requireActivity().applicationContext as WeatherApplication)
//            .networkService
//            .getApiService()
//            .forecastWeatherByCityRx("Izmail", "22d417de39746d69715379f3d114e766", "metric")
//            .subscribe(object: SingleObserver<ForecastList> {
//                override fun onSuccess(value: ForecastList?) {
//                   Log.d("hello", "FROM SUCCESS")
//
//                }
//
//                override fun onSubscribe(d: Disposable?) {
//                    Log.d("hello", "FROM SUBSCRIBE")
//                    val value: ForecastList?
//                }
//
//                override fun onError(e: Throwable?) {
//                    Log.d("hello", "FROM ERROR")
//                }
//            })
//        val call: Call<ForecastList> =
//            (requireActivity().applicationContext as WeatherApplication)
//                .networkService
//                .getApiService()
//                .forecastWeatherForCity("Izmail", "22d417de39746d69715379f3d114e766", "metric")
//        call.enqueue(object : retrofit2.Callback<ForecastList> {
//            override fun onFailure(call: Call<ForecastList>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<ForecastList>, response: Response<ForecastList>) {
//                val mResponse = response.body()
//                recyclerView.adapter = mResponse?.let { RecyclerViewAdapterForecast(it) }
//
//            }
//        })
        return view
    }

}
