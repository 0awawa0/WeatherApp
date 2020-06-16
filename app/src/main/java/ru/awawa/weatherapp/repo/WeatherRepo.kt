package ru.awawa.weatherapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.awawa.weatherapp.Application
import ru.awawa.weatherapp.repo.retrofit.apis.CurrentWeatherApi
import ru.awawa.weatherapp.repo.retrofit.models.CurrentWeatherModel


class WeatherRepo: KoinComponent {

    private val currentWeatherApi: CurrentWeatherApi by inject()

    private val _currentWeatherModel: MutableLiveData<CurrentWeatherModel> = MutableLiveData()
    val currentWeatherModel: LiveData<CurrentWeatherModel> = _currentWeatherModel

    val cityId: MutableLiveData<Long> = MutableLiveData()
    init {
        cityId.observeForever {
            updateCurrentWeather(it)
        }
        cityId.value = 0
    }

    fun updateCurrentWeather(cityId: Long) {
        GlobalScope.launch (Dispatchers.IO) {
            val response = currentWeatherApi.getCurrentWeatherById(
                cityId,
                API_KEY
            ).execute()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    launch (Dispatchers.Main) {
                        _currentWeatherModel.value = body
                    }
                }
            }
        }
    }
}