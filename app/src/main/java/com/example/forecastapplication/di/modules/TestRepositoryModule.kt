package com.example.forecastapplication.di.modules

import com.example.forecastapplication.core.repository.ITestRepository
import com.example.forecastapplication.core.repository.TestRepository
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