package com.example.forecastapplication.model.data

import com.google.gson.annotations.SerializedName

data class CurrentResponse(
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("base")
    val base: String,
    @SerializedName("main")
    val main: TemperatureData,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("cod")
    val cod: Int
)

data class Sys(
    @SerializedName("type")
    val type: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("message")
    val message: Double = 0.0,
    @SerializedName("country")
    val country: String = "",
    @SerializedName("sunrise")
    val sunrise: Int = 0,
    @SerializedName("sunset")
    val sunset: Int = 0
)

data class TemperatureData(
    @SerializedName("temp")
    val temp: Double = 0.0,
    @SerializedName("pressure")
    val pressure: Int = 0,
    @SerializedName("humidity")
    val humidity: Int = 0,
    @SerializedName("temp_min")
    val tempMin: Double = 0.0,
    @SerializedName("temp_max")
    val tempMax: Double = 0.0
)

data class Weather(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("main")
    val main: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("icon")
    val icon: String = ""
)

data class Clouds(
    @SerializedName("all")
    val all: Int = 0
)

data class Wind(
    @SerializedName("speed")
    val speed: Double = 0.0,
    @SerializedName("deg")
    val deg: Int = 0
)