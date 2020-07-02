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

    private val _result: MutableLiveData<OperationResult> = MutableLiveData()
    val result: LiveData<OperationResult> = _result

    private val _oneCallModel: MutableLiveData<OneCallModel> = MutableLiveData()
    val oneCallModel: LiveData<OneCallModel> = _oneCallModel

    private val _cityName: MutableLiveData<String> = MutableLiveData()
    val cityName: LiveData<String> = _cityName
    var city: City? = null
        set(value) {
            if (value != null) {
                _cityName.value = value.name
                _result.value = OperationResult(ResultType.LOADING, "Loading data")
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
            try {
                val call = oneCallApi.getForecast(latitude, longitude, API_KEY)
                Log.d("WeatherRepo", "${call.request().url().url()}")
                val response = call.execute()

                if (response.isSuccessful) {
                    val body = response.body()

                    if (body != null) {
                        launch(Dispatchers.Main) {
                            _result.value = OperationResult(ResultType.SUCCESS, "Data loaded")
                            _oneCallModel.value = body
                        }
                    }
                } else {
                    launch(Dispatchers.Main) {
                        _result.value = OperationResult(
                            ResultType.ERROR,
                            "Data loading failed: ${response.errorBody()!!.string()}"
                        )
                    }
                    Log.e("WeatherRepo", "Request failed: ${response.errorBody()!!.string()}")
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    _result.value = OperationResult(
                        ResultType.ERROR,
                        "Data loading failed. Check internet connection and try again"
                    )
                }
            }
        }
    }

    enum class ResultType { LOADING, SUCCESS, ERROR }

    inner class OperationResult(val type: ResultType, val message: String)
}