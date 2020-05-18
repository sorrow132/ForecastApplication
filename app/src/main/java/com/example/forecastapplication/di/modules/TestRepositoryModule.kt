package com.example.forecastapplication.di.modules

import com.example.forecastapplication.core.ITestRepository
import com.example.forecastapplication.core.TestRepository
import com.example.forecastapplication.core.repository.IDBRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestRepositoryModule {

    @Provides
    @Singleton
    fun provideTestRepository(db: IDBRepository): ITestRepository {
        return TestRepository(db)
    }
}