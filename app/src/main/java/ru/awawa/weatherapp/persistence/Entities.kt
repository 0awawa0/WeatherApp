package ru.awawa.weatherapp.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.awawa.weatherapp.retrofit.models.CoordinateModel


@Entity(tableName="City")
data class City (
    @PrimaryKey val id: Long,
    val name: String,
    val state: String,
    val country: String,
    val lon: Float,
    val lat: Float
)