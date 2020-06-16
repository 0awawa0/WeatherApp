package ru.awawa.weatherapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.awawa.weatherapp.persistence.City
import ru.awawa.weatherapp.persistence.Database

class SearchViewModel: ViewModel(), KoinComponent {

    private val database: Database by inject()
    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> = _cities

    fun doSearch(query: String) {
        GlobalScope.launch(Dispatchers.IO) {
            launch(Dispatchers.Main) { _cities.value = database.cityDao().findByName(query) }
        }
    }
}