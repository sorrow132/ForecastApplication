package com.example.forecastapplication.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CityEntity::class], version = 2)
abstract class CityDatabase : RoomDatabase() {
    abstract fun locationDao(): CityDao

    companion object {

        fun createDatabase(context: Context): CityDatabase {
            return Room
                .databaseBuilder(context, CityDatabase::class.java, "MyDb")
                .build()
        }
    }
}
