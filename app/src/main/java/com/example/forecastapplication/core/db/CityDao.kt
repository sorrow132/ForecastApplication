package com.example.forecastapplication.core.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: CityEntity): Completable

    @Query("SELECT * FROM base_location where city == city ")
    fun getCity(): Observable<List<CityEntity>>

    @Delete
    fun deleteCity(city: CityEntity): Completable
}