package com.example.forecastapplication.view.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.model.IViewObject
import com.example.forecastapplication.model.TomorrowWeatherModel
import com.example.forecastapplication.view.ui.weather.tomorrow.CustomViewHolderTomorrow

class RecyclerViewAdapterTomorrow(
    private val infoList: List<IViewObject>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ID_WEATHER = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == RecyclerViewAdapterTomorrow.VIEW_TYPE_ID_WEATHER) {
            return CustomViewHolderTomorrow(
                parent
            )
        }
        throw  IllegalArgumentException("Wrong element")
    }

    override fun getItemViewType(position: Int): Int {
        if (infoList[position] is TomorrowWeatherModel)
            return RecyclerViewAdapterTomorrow.VIEW_TYPE_ID_WEATHER
        throw IllegalArgumentException("Wrong element")
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CustomViewHolderTomorrow) {
            holder.bind(infoList[position] as TomorrowWeatherModel)
        }
    }
}