package ru.awawa.weatherapp.repo.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CityDao {

    @Query("SELECT * FROM City")
    fun getAllCities(): List<City>

    @Query("SELECT * FROM City WHERE name LIKE :name || '%' ORDER BY name ASC LIMIT 20")
    fun findByName(name: String): List<City>?

    @Query("SELECT * FROM City WHERE id == :id")
    fun findById(id: Long): City?

    @Query("SELECT COUNT(*) FROM City")
    fun getDbSize(): Int

    @Insert
    fun insertAll(vararg city: City)

    @Insert
    fun insertCity(city: City)

    @Delete
    fun deleteCity(city: City)

    @Query("DELETE FROM City")
    fun clearDb()
}