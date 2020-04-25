package com.example.forecastapplication.view.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.Common
import com.example.forecastapplication.R
import com.example.forecastapplication.model.data.ForecastList
import com.squareup.picasso.Picasso

class RecyclerViewAdapterTomorrow(
    private val arr1: MutableList<String>,
    private val arr2: MutableList<String>,
    private val arr3: MutableList<String>
) :
    RecyclerView.Adapter<RecyclerViewAdapterTomorrow.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewForHours: TextView? = itemView.findViewById(R.id.textView_hour_one)
        var textViewForWeather: TextView? = itemView.findViewById(R.id.textView_weather_one)
        var weatherIconTomorrow: ImageView? =
            itemView.findViewById(R.id.imageView_condition_icon_tomorrow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_list_of_items, parent, false)
        return CustomViewHolder(
            itemView
        )
    }

    override fun getItemCount() = arr1.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.textViewForHours?.text =
            arr1[position].substring(11, 16)
        holder.textViewForWeather?.text =
            arr3[position] + "°C"
        Picasso.get()
            .load("https://openweathermap.org/img/wn/" + arr2[position] + ".png")
            .into(holder.weatherIconTomorrow)
    }
}