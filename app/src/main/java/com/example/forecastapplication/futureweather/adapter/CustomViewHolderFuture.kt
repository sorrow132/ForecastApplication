package com.example.forecastapplication.futureweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.R
import com.example.forecastapplication.futureweather.model.FutureWeatherModel
import com.squareup.picasso.Picasso

class CustomViewHolderFuture(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.forecast_weather_item, parent, false)
) {
    private val textViewTemp: TextView = itemView.findViewById(R.id.textViewTemperatureItemForecast)
    private val textViewStatus: TextView = itemView.findViewById(R.id.textViewConditionItemForecast)
    private val textViewDate: TextView = itemView.findViewById(R.id.textViewDateItemForecast)
    private val textViewHour: TextView = itemView.findViewById(R.id.textViewHourForecast)
    private val imageViewIcon: ImageView = itemView.findViewById(R.id.weatherIconItemForecast)

    fun bind(model: FutureWeatherModel) {
        textViewTemp.text = model.temp
        textViewStatus.text = model.status
        textViewDate.text = model.date
        textViewHour.text = model.hour
        Picasso.get()
            .load(model.icon)
            .into(imageViewIcon)

    }
}