package com.example.forecastapplication.core.db

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: CityEntity): Completable

    @Update
    fun update(city: CityEntity)

    @Query("SELECT * FROM base_location where city == city ")
    fun getCity(): Observable<List<CityEntity>>

    @Delete
    fun deleteCity(city: CityEntity): Completable

    @Query("SELECT * FROM base_location where isSelected == 1 LIMIT 1")
    fun getSelectedCity(): CityEntity

    @Transaction
    fun updateSelectedCity(city: CityEntity) {
        val currentSelectedCity: CityEntity? = getSelectedCity()
        if(currentSelectedCity != null) {
            update(currentSelectedCity.copy(isSelected = 0))
        }
        update(city.copy(isSelected = 1))
    }
}