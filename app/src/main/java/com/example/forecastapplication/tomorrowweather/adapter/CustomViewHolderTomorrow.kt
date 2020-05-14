package com.example.forecastapplication.tomorrowweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.R
import com.example.forecastapplication.tomorrowweather.model.TomorrowWeatherModel
import com.squareup.picasso.Picasso

class CustomViewHolderTomorrow(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.weather_list_of_item, parent, false)
) {
    var textViewForHours: TextView? = itemView.findViewById(R.id.textView_hour_one)
    var textViewForWeather: TextView? = itemView.findViewById(R.id.textView_weather_one)
    var weatherIconTomorrow: ImageView? =
        itemView.findViewById(R.id.imageView_condition_icon_tomorrow)

    fun bind(model: TomorrowWeatherModel) {
        textViewForHours?.text = model.date
        textViewForWeather?.text = model.temp

        Picasso.get()
            .load(model.image)
            .into(weatherIconTomorrow)
    }
}