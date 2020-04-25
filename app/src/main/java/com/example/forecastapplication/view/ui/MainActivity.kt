package com.example.forecastapplication.view.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.forecastapplication.R
import com.example.forecastapplication.view.ui.adapter.VPAdapter
import com.example.forecastapplication.view.ui.current.CurrentWeatherFragment
import com.example.forecastapplication.view.ui.future.ForecastWeatherFragment
import com.example.forecastapplication.view.ui.tomorrow.TomorrowWeatherFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vpAdapter =
            VPAdapter(
                supportFragmentManager
            )
        vpAdapter.addFragment(CurrentWeatherFragment(), "Current")
        vpAdapter.addFragment(TomorrowWeatherFragment(), "Tomorrow")
        vpAdapter.addFragment(ForecastWeatherFragment(), "5 Days")
        view_pager.adapter = vpAdapter
        tab_layout.setupWithViewPager(view_pager)
    }

    @SuppressLint("ServiceCast")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_search, menu)
        val searchItem = menu?.findItem(R.id.search_toolbar)

        return true
    }
}

