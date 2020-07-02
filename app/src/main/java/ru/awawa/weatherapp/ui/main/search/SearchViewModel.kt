package ru.awawa.weatherapp.ui.main.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.inject
import ru.awawa.weatherapp.repo.CitiesRepo
import ru.awawa.weatherapp.repo.persistence.City
import ru.awawa.weatherapp.ui.main.BaseViewModel

class SearchViewModel: BaseViewModel() {

    private val citiesRepo: CitiesRepo by inject()

    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> = _cities

    fun doSearch(query: String) {
        GlobalScope.launch(Dispatchers.IO) {
            launch(Dispatchers.Main) { _cities.value =  citiesRepo.getCities(query) }
        }
    }
}