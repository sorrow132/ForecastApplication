package com.example.forecastapplication.di.modules

import com.example.forecastapplication.core.repository.IDBRepository
import com.example.forecastapplication.core.repository.IRepository
import com.example.forecastapplication.core.repository.RepositoryImpl
import com.example.forecastapplication.core.request.WeatherApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(network: WeatherApiService, db: IDBRepository): IRepository {
        return RepositoryImpl(
            network,
            db
        )
    }
}