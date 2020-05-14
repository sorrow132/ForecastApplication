package com.example.forecastapplication.di.components

import com.example.forecastapplication.WeatherApplication
import com.example.forecastapplication.di.modules.DatabaseModule
import com.example.forecastapplication.di.modules.NetworkModule
import com.example.forecastapplication.di.modules.RepositoryModule
import com.example.forecastapplication.cities.view.ListOfCityFragment
import com.example.forecastapplication.currentweather.view.CurrentWeatherFragment
import com.example.forecastapplication.futureweather.view.FutureWeatherFragment
import com.example.forecastapplication.tomorrowweather.view.TomorrowWeatherFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, RepositoryModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appContext(app: WeatherApplication): Builder
        fun build(): AppComponent
    }

    fun inject(provider: ListOfCityFragment)
    fun inject(provider: CurrentWeatherFragment)
    fun inject(provider: TomorrowWeatherFragment)
    fun inject(provider: FutureWeatherFragment)


}