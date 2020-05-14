package com.example.forecastapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.forecastapplication.adapter.VPAdapter
import com.example.forecastapplication.cities.view.ListOfCityFragment
import com.example.forecastapplication.currentweather.view.CurrentWeatherFragment
import com.example.forecastapplication.futureweather.view.FutureWeatherFragment
import com.example.forecastapplication.tomorrowweather.view.TomorrowWeatherFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vpAdapter = VPAdapter(
            supportFragmentManager
        )
        vpAdapter.addFragment(ListOfCityFragment(), "Location")
        vpAdapter.addFragment(CurrentWeatherFragment(), "Current")
        vpAdapter.addFragment(TomorrowWeatherFragment(), "Tomorrow")
        vpAdapter.addFragment(FutureWeatherFragment(), "5 Days")
        view_pager.adapter = vpAdapter
        tab_layout.setupWithViewPager(view_pager)
    }

}

