package com.example.forecastapplication.cities.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.cities.model.CityModel
import com.example.forecastapplication.R
import com.example.forecastapplication.core.db.CityEntity

class CustomViewHolderList(
    parent: ViewGroup,
    private val onRemoveCityListener: (CityEntity) -> Unit,
    private val onSelectCityListener: (CityEntity) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.list_of_city_item, parent, false
    )
) {
    private val textViewName: TextView = itemView.findViewById(R.id.listCard_name)
    private val removeButton: ImageView = itemView.findViewById(R.id.btnDelete)
    private val selectedItem: LinearLayout = itemView.findViewById(R.id.linearInCardView)


    fun bind(model: CityModel) {
        textViewName.text = model.entity.city
        removeButton.setOnClickListener {
            onRemoveCityListener(model.entity)
        }
        selectedItem.setOnClickListener {
            onSelectCityListener(model.entity)
        }

        if (model.entity.isSelected == 1) {
            selectedItem.setBackgroundColor(Color.GRAY)
        } else {
            selectedItem.setBackgroundColor(Color.WHITE)
        }
    }
}