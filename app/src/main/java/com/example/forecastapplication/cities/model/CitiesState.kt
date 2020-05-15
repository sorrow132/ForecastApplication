package com.example.forecastapplication.cities.model

sealed class CitiesState {

    object Loading : CitiesState()

    object EmptyInfo : CitiesState()

    data class LoadInfo(
        val city: List<CityModel>
    ) : CitiesState()

    data class AddNew(
        val city: CityModel
    ) : CitiesState()

    data class Error(
        val error: Throwable
    ) : CitiesState()

}