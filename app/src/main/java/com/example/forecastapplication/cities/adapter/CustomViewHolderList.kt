package com.example.forecastapplication.cities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.cities.model.CityModel
import com.example.forecastapplication.R

class CustomViewHolderList(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.list_of_city_item, parent, false
    )
) {
    private val textViewName: TextView = itemView.findViewById(R.id.listCard_name)

    fun bind(model: CityModel) {
        textViewName.text = model.city
    }
}