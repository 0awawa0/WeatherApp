package ru.awawa.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.awawa.weatherapp.Application
import ru.awawa.weatherapp.retrofit.apis.CurrentWeatherApi
import ru.awawa.weatherapp.retrofit.models.CoordinateModel
import ru.awawa.weatherapp.retrofit.models.MainWeatherDataModel
import ru.awawa.weatherapp.retrofit.models.WindModel

class CurrentWeatherViewModel: ViewModel(), KoinComponent {

    private val currentWeatherApi: CurrentWeatherApi by inject()

    private val _cityName = MutableLiveData<String>()
    private val _coordinate = MutableLiveData<CoordinateModel>()
    private val _mainData = MutableLiveData<MainWeatherDataModel>()
    private val _wind = MutableLiveData<WindModel>()

    val cityName: LiveData<String> = _cityName
    val coordinate: LiveData<CoordinateModel> = _coordinate
    val mainData: LiveData<MainWeatherDataModel> = _mainData
    val wind: LiveData<WindModel> = _wind

    val coordinatesString: String
        get() {
            if (coordinate.value == null) return ""
            return "Longitude: ${coordinate.value!!.longitude} Latitude: ${coordinate.value!!.latitude}"
        }

    val currentTemperature: String
        get() {
            if (mainData.value == null) return ""
            return "Temperature: ${mainData.value!!.temperature - 273}°C"
        }

    val minTemperature: String
        get() {
            if (mainData.value == null) return ""
            return "Min temperature: ${mainData.value!!.minTemperature - 273}°C"
        }

    val maxTemperature: String
        get() {
            if (mainData.value == null) return ""
            return "Max temperature: ${mainData.value!!.maxTemperature - 273}°C"
        }

    fun getCurrentWeatherByName(city: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = currentWeatherApi
                .getCurrentWeatherByName(city, Application.API_KEY)
                .execute()
            if (response.isSuccessful) {
                val currentWeatherModel = response.body()
                launch(Dispatchers.Main) {
                    if (currentWeatherModel != null) {
                        _cityName.value = currentWeatherModel.cityName
                        _coordinate.value = currentWeatherModel.coordinates
                        _mainData.value = currentWeatherModel.mainData
                        _wind.value = currentWeatherModel.wind
                    }
                }
            }
        }
    }

    fun getCurrentWeatherById(cityId: Long) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = currentWeatherApi
                .getCurrentWeatherById(cityId, Application.API_KEY)
                .execute()

            if (response.isSuccessful) {
                val currentWeatherModel = response.body()
                launch (Dispatchers.Main) {
                    if (currentWeatherModel != null) {
                        _cityName.value = currentWeatherModel.cityName
                        _coordinate.value = currentWeatherModel.coordinates
                        _mainData.value = currentWeatherModel.mainData
                        _wind.value = currentWeatherModel.wind
                    }
                }
            }
        }
    }

}