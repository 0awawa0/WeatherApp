package ru.awawa.weatherapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase


const val DATABASE_NAME = "ru.awawa.weatherapp.cities"

@Database(
    entities = [City::class],
    version = 3,
    exportSchema = false
)
abstract class Database: RoomDatabase() {
    abstract fun cityDao(): CityDao
}