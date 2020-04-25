package com.example.forecastapplication.view.ui.tomorrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.R

class TomorrowWeatherFragment: Fragment() {
//    private lateinit var textViewTemperature: TextView
//    private lateinit var textViewWind: TextView
//    private lateinit var textViewHumidity: TextView
//    private lateinit var textViewPressure: TextView
//    private lateinit var progressBar: ProgressBar
//    private lateinit var feelsLike: TextView
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var weatherStatusTomorrow: ImageView
//    private lateinit var textViewLoading: TextView
//    private lateinit var textViewWeatherCondition: TextView
//
//    private var arr1: MutableList<String> = ArrayList()
//    private var arr2: MutableList<String> = ArrayList()
//    private var arr3: MutableList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tomorrow_weather, container, false)
//        textViewTemperature = view.findViewById(R.id.temperature_text_view)
//        textViewWind = view.findViewById(R.id.textView_wind_current)
//        textViewHumidity = view.findViewById(R.id.textView_precipitation)
//        textViewPressure = view.findViewById(R.id.textView_visibility)
//        progressBar = view.findViewById(R.id.progressBar_loading)
//        weatherStatusTomorrow = view.findViewById(R.id.imageView_condition_icon_tomorrow)
//        feelsLike = view.findViewById(R.id.textView_feels_like_temperature)
//        progressBar = view.findViewById(R.id.progressBar_loading)
//        textViewLoading = view.findViewById(R.id.textView_loading)
//        recyclerView = view.findViewById(R.id.recycler_view)
//        textViewWeatherCondition = view.findViewById(R.id.textView_condition)
//        recyclerView.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)



//
//        val call: Call<ForecastList> =
//            (requireActivity().applicationContext as WeatherApplication)
//                .networkService
//                .getApiService()
//                .forecastWeatherForCity("Izmail", "22d417de39746d69715379f3d114e766", "metric")
//        call.enqueue(object : retrofit2.Callback<ForecastList> {
//            override fun onFailure(call: Call<ForecastList>, t: Throwable) {
//                textViewTemperature.append("Error occurred while getting request")
//                t.printStackTrace()
//                textViewWind.append("Error occurred while getting request")
//                t.printStackTrace()
//                textViewHumidity.append("Error occurred while getting request")
//                t.printStackTrace()
//                textViewPressure.append("Error occurred while getting request")
//                t.printStackTrace()
//            }
//
//            override fun onResponse(
//                call: Call<ForecastList>,
//                response: Response<ForecastList>
//            ) {
//                val mResponse = response.body()
//
//                for (i in mResponse!!.weatherList.indices) {
//                    if (Common.convertToDate(mResponse.weatherList[i].dateTxt) == 16) {
//
//                        arr1.add(mResponse.weatherList[i].dateTxt)
//                        arr2.add(mResponse.weatherList[i].weather[0].icon)
//                        arr3.add(mResponse.weatherList[i].main.temp.toString())
//
//                        recyclerView.adapter = RecyclerViewAdapterTomorrow(arr1, arr2, arr3)
//                        recyclerView.addItemDecoration(object :
//                            DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL) {})
//
//                        textViewTemperature.text =
//                            mResponse!!.weatherList[0].main.temp.toString() + "°C"
//                        textViewWeatherCondition.text = mResponse.weatherList[0].weather[0].main
//                        feelsLike.text =
//                            "Feels like: " + mResponse!!.weatherList[0].main.feelsLike.toString() + "°C"
//                        textViewWind.text =
//                            "Wind: SE, " + mResponse.weatherList[0].wind.speed.toString() + " m/s"
//                        textViewHumidity.text =
//                            "Humidity: " + mResponse.weatherList[0].main.humidity.toString() + "%"
//                        textViewPressure.text =
//                            "Pressure: " + mResponse.weatherList[0].main.pressure.toString()
//                        Picasso.get()
//                            .load("https://openweathermap.org/img/wn/" + mResponse.weatherList[i].weather[0].icon + ".png")
//                            .into(weatherStatusTomorrow)
//
//                        progressBar.visibility = View.GONE
//                        textViewLoading.visibility = View.GONE
//                    }
//                }
//
//            }
//        })
        return view
    }
}
