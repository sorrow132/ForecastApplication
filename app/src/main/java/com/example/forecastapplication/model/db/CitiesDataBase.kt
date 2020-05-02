package com.example.forecastapplication.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CitiesEntity::class], version = 1)
abstract class CitiesDataBase : RoomDatabase() {
    abstract fun locationDao(): BaseLocationDao

    companion object {
        var INSTANCE: CitiesDataBase? = null

        fun getCitiesDataBase(context: Context): CitiesDataBase? {
            if (INSTANCE == null) {
                synchronized(CitiesDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CitiesDataBase::class.java,
                        "myDB"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}