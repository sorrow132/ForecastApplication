package com.example.forecastapplication.view.ui.weather.future

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.R
import com.example.forecastapplication.model.FutureWeatherModel
import com.squareup.picasso.Picasso

class CustomViewHolderFuture(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.forecast_weather_item, parent, false)
) {
    private val textViewTemp: TextView = itemView.findViewById(R.id.textViewTemperatureItemForecast)
    private val textViewStatus: TextView = itemView.findViewById(R.id.textViewConditionItemForecast)
    private val textViewDate: TextView = itemView.findViewById(R.id.textViewDateItemForecast)
    private val imageViewIcon: ImageView = itemView.findViewById(R.id.weatherIconItemForecast)

    fun bind(model: FutureWeatherModel) {
        textViewTemp.text = model.temp
        textViewStatus.text = model.status
        textViewDate.text = model.date
        Picasso.get()
            .load(model.icon)
            .into(imageViewIcon)

    }
}