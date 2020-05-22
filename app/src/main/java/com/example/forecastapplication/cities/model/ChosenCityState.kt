package com.example.forecastapplication.cities.model

import com.example.forecastapplication.core.db.CityEntity

sealed class ChosenCityState {

    data class Exist(
        val city: CityEntity
    ) : ChosenCityState()

    object NonExist : ChosenCityState()

}