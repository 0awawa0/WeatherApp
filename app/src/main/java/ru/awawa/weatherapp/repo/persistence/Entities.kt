package ru.awawa.weatherapp.repo.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="City")
data class City (
    @PrimaryKey val id: Long,
    val name: String,
    val state: String,
    val country: String,
    val lon: Float,
    val lat: Float
)