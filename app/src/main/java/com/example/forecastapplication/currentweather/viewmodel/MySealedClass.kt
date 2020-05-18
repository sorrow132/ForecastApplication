package com.example.forecastapplication.currentweather.viewmodel

import com.example.forecastapplication.core.db.CityEntity

sealed class MySealedClass {

    data class Exist(
        val myString: CityEntity
    ) : MySealedClass()

    object NonExist : MySealedClass()

}