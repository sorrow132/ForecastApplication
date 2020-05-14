package com.example.forecastapplication.di.modules

import com.example.forecastapplication.WeatherApplication
import com.example.forecastapplication.core.db.CityDatabase
import com.example.forecastapplication.core.db.DatabaseImpl
import com.example.forecastapplication.core.repository.IDBRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRepository(app: WeatherApplication): IDBRepository {
        return DatabaseImpl(
            CityDatabase.createDatabase(
                app
            ).locationDao()
        )
    }
}