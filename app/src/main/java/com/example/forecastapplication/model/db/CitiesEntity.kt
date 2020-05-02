package com.example.forecastapplication.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "base_location")
data class CitiesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val city: String
)