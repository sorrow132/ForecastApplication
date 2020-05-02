package com.example.forecastapplication.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.forecastapplication.R
import com.example.forecastapplication.view.ui.adapters.VPAdapter
import com.example.forecastapplication.view.ui.weather.current.CurrentWeatherFragment
import com.example.forecastapplication.view.ui.weather.future.FutureWeatherFragment
import com.example.forecastapplication.view.ui.weather.tomorrow.TomorrowWeatherFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vpAdapter = VPAdapter(supportFragmentManager)
        vpAdapter.addFragment(CurrentWeatherFragment(), "Current")
        vpAdapter.addFragment(TomorrowWeatherFragment(), "Tomorrow")
        vpAdapter.addFragment(FutureWeatherFragment(), "5 Days")
        view_pager.adapter = vpAdapter
        tab_layout.setupWithViewPager(view_pager)

    }
}

