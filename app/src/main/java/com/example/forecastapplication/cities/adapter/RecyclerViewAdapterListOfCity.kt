package com.example.forecastapplication.cities.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.cities.model.CityModel
import com.example.forecastapplication.core.IViewObject
import com.example.forecastapplication.core.db.CityEntity
import java.lang.IllegalArgumentException

class RecyclerViewAdapterListOfCity(
    private val infoList: List<IViewObject>,
    private val onRemoveCityListener: (CityEntity) -> Unit,
    private val onSelectCityListener: (CityEntity) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ID_WEATHER = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ID_WEATHER) {
            return CustomViewHolderList(
                parent,
                onRemoveCityListener,
                onSelectCityListener
            )
        }
        throw IllegalArgumentException("Wrong element")
    }

    override fun getItemViewType(position: Int): Int {
        if (infoList[position] is CityModel) {
            return VIEW_TYPE_ID_WEATHER
        }
        throw IllegalArgumentException("Wrong element")
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CustomViewHolderList) {
            holder.bind(infoList[position] as CityModel)
        }
    }
}