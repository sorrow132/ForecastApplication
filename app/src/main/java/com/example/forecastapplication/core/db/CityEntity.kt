package com.example.forecastapplication.core.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "base_location")
data class CityEntity(
    @PrimaryKey
    val city: String,
    val isSelected: Int
)