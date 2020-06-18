package ru.awawa.weatherapp.repo

import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.awawa.weatherapp.repo.persistence.City
import ru.awawa.weatherapp.repo.persistence.Database


class CitiesRepo: KoinComponent {

    private val database: Database by inject()

    fun getCities(name: String): List<City>? {
        return database.cityDao().findByName(name)
    }

    fun getCity(id: Long): City? {
        return database.cityDao().findById(id)
    }
}