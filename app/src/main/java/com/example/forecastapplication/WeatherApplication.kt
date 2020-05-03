package com.example.forecastapplication

import android.app.Application
import androidx.room.Room
import com.example.forecastapplication.model.db.CitiesDataBase

class WeatherApplication : Application() {
    companion object {
//        lateinit var db: CitiesDataBase
    }

    override fun onCreate() {
        super.onCreate()
//        db = Room.databaseBuilder(WeatherApplication(), CitiesDataBase::class.java, "CitiesDB")
//            .build()
    }
}