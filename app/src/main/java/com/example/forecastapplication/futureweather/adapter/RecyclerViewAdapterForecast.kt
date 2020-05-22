package com.example.forecastapplication.futureweather.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.core.IViewObject
import com.example.forecastapplication.futureweather.model.FutureWeatherModel

class RecyclerViewAdapterForecast(
    private val infoList: List<IViewObject>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_ID_WEATHER = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ID_WEATHER) {
            return CustomViewHolderFuture(parent)
        }
        throw  IllegalArgumentException("Wrong element")
    }

    override fun getItemViewType(position: Int): Int {
        if (infoList[position] is FutureWeatherModel)
            return VIEW_TYPE_ID_WEATHER
        throw IllegalArgumentException("Wrong element")
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CustomViewHolderFuture) {
            holder.bind(infoList[position] as FutureWeatherModel)
        }
    }
}
