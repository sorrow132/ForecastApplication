package com.example.forecastapplication.model.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class ForecastList(
    @SerializedName("list")
    val weatherList: List<ForecastEntities>
)

data class ForecastEntities(
    val main: MainWeatherInfo,
    @SerializedName("weather")
    val weather: List<WeatherForecast>,
    @SerializedName("wind")
    val wind: WindForecast,
    @SerializedName("dt_txt")
    val dateTxt: String = ""
)

data class MainWeatherInfo(
    @SerializedName("temp")
    val temp: Double = 0.0,
    @SerializedName("feels_like")
    val feelsLike: Double = 0.0,
    @SerializedName("temp_min")
    val tempMin: Double = 0.0,
    @SerializedName("temp_max")
    val tempMax: Double = 0.0,
    @SerializedName("pressure")
    val pressure: Int = 0,
    @SerializedName("sea_level")
    val seaLevel: Int = 0,
    @SerializedName("grnd_level")
    val grndLevel: Int = 0,
    @SerializedName("humidity")
    val humidity: Int = 0,
    @SerializedName("temp_kf")
    val tempKf: Double = 0.0
)

data class WeatherForecast(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("main")
    val main: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("icon")
    val icon: String = ""
)

data class WindForecast(
    val speed: Double = 0.0,
    val deg: Int = 0
)

