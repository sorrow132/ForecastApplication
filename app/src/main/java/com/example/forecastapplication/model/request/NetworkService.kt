package com.example.forecastapplication.model.request

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val API_KEY: String = "22d417de39746d69715379f3d114e766"
private const val BASE_URL: String = "https://api.openweathermap.org/data/2.5/"

class NetworkService {
    private val logging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val httpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(logging)

    private val okHttp = httpClient.build()

    private val service: WeatherApiService = Retrofit.Builder()
        .client(okHttp)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApiService::class.java)

    fun getApiService(): WeatherApiService {
        return service
    }
}

