package com.example.forecastapplication.model.db

import androidx.room.*
import io.reactivex.Single

@Dao
interface BaseLocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(baseLocation: CitiesEntity)

    @Query("select * from base_location where city == city ")
    fun getCity(): Single<CitiesEntity>

    @Delete
    fun deleteCity(baseLocation: CitiesEntity)
}