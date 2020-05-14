package com.example.forecastapplication.cities.model

sealed class CitiesState {

    object Loading : CitiesState()

    data class LoadInfo(
        val city: CityModel
    ) : CitiesState()

    data class Error(
        val error: Throwable
    ) : CitiesState()

}