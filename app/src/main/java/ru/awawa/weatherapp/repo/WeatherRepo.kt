package ru.awawa.weatherapp.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.awawa.weatherapp.repo.persistence.City
import ru.awawa.weatherapp.repo.preferences.Preferences
import ru.awawa.weatherapp.repo.retrofit.apis.OneCallApi
import ru.awawa.weatherapp.repo.retrofit.models.onecall.OneCallModel
import ru.awawa.weatherapp.repo.retrofit.utils.API_KEY


class WeatherRepo: KoinComponent {

    private val oneCallApi: OneCallApi by inject()
    private val preferences: Preferences by inject()
    private val citiesRepo: CitiesRepo by inject()

    private val _oneCallModel: MutableLiveData<OneCallModel> = MutableLiveData()
    val oneCallModel: LiveData<OneCallModel> = _oneCallModel

    private val _cityName: MutableLiveData<String> = MutableLiveData()
    val cityName: LiveData<String> = _cityName
    var city: City? = null
        set(value) {
            if (value != null) {
                _cityName.value = value.name
                updateOneCallModel(value.lat, value.lon)
                preferences.currentCity = value.id
                field = value
            }
        }

    init {
        GlobalScope.launch (Dispatchers.IO) {
            val city = citiesRepo.getCity(preferences.currentCity)
            launch(Dispatchers.Main) {
                this@WeatherRepo.city = city
            }
        }
    }

    fun updateData() {
        if (city != null) {
            updateOneCallModel((city as City).lat, (city as City).lon)
        }
    }

    private fun updateOneCallModel(latitude: Float, longitude: Float) {
        GlobalScope.launch (Dispatchers.IO) {
            val call = oneCallApi.getForecast(latitude, longitude, API_KEY)
            Log.d("WeatherRepo", "${call.request().url().url()}")
            val response = call.execute()

            if (response.isSuccessful) {
                val body = response.body()

                if (body != null) {
                    launch (Dispatchers.Main) {
                        _oneCallModel.value = body
                    }
                }
            } else {
                Log.e("WeatherRepo", "Request failed: ${response.errorBody()!!.string()}")
            }
        }
    }
}