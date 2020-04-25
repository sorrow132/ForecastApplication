package com.example.forecastapplication.view.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.R
import com.example.forecastapplication.model.data.ForecastList
import com.squareup.picasso.Picasso

class RecyclerViewAdapterForecast(private val forecast: ForecastList) :
    RecyclerView.Adapter<RecyclerViewAdapterForecast.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTemp: TextView = itemView.findViewById(R.id.text_view_temperature_item_in_item)
        var textViewWeatherCondition: TextView =
            itemView.findViewById(R.id.text_view_condition_in_item)
        var weatherIconForecast: ImageView = itemView.findViewById(R.id.weather_icon_in_item)
        var dateForWeatherByDay: TextView = itemView.findViewById(R.id.text_view_date_in_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.forecast_weather_items, parent, false)
        return CustomViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int {
        return forecast.weatherList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.textViewTemp.text = forecast.weatherList[position].main.temp.toString() + "°C"
        holder.textViewWeatherCondition.text = forecast.weatherList[position].weather[0].main
        holder.dateForWeatherByDay.text = forecast.weatherList[position].dateTxt.substring(0, 11)
        Picasso.get()
            .load("https://openweathermap.org/img/wn/" + forecast.weatherList[position].weather[0].icon + ".png")
            .into(holder.weatherIconForecast)
    }
}