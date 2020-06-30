package ru.awawa.weatherapp.ui.main.current

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.awawa.weatherapp.repo.WeatherRepo
import ru.awawa.weatherapp.repo.retrofit.models.onecall.OneCallModel
import ru.awawa.weatherapp.utils.Helpers

class CurrentWeatherViewModel: ViewModel(), KoinComponent {

    val weatherRepo: WeatherRepo by inject()

    val currentWeatherModel = weatherRepo.oneCallModel
    val cityName = weatherRepo.cityName
    val latitude: MutableLiveData<Float> = MutableLiveData()
    val longitude: MutableLiveData<Float> = MutableLiveData()

    val temperature: MutableLiveData<String> = MutableLiveData()
    val feelsLike: MutableLiveData<String> = MutableLiveData()

    val pressure: MutableLiveData<String> = MutableLiveData()

    val dewPoint: MutableLiveData<String> = MutableLiveData()
    val humidity: MutableLiveData<Int> = MutableLiveData()

    var clouds: MutableLiveData<Int> = MutableLiveData()
    var uvIndex: MutableLiveData<Float> = MutableLiveData()

    var visibility: MutableLiveData<Int> = MutableLiveData()

    var windSpeed: MutableLiveData<Float> = MutableLiveData()
    var windGust: MutableLiveData<Float> = MutableLiveData()
    var windDirection: MutableLiveData<String> = MutableLiveData()

    private val observer = Observer<OneCallModel> {
        latitude.value = weatherRepo.city?.lat
        longitude.value = weatherRepo.city?.lon
        temperature.value = Helpers.formatDegreesToCelsius(currentWeatherModel.value?.current?.temperature)
        feelsLike.value = Helpers.formatDegreesToCelsius(currentWeatherModel.value?.current?.feelsLike)
        dewPoint.value = Helpers.formatDegreesToCelsius(currentWeatherModel.value?.current?.dewPoint)
        humidity.value = currentWeatherModel.value?.current?.humidity?.toInt()
        clouds.value = currentWeatherModel.value?.current?.clouds
        uvIndex.value = currentWeatherModel.value?.current?.uvIndex
        visibility.value = currentWeatherModel.value?.current?.visibility
        windSpeed.value = currentWeatherModel.value?.current?.windSpeed
        windGust.value = currentWeatherModel.value?.current?.windGust
        windDirection.value = Helpers.degreesToDirection(currentWeatherModel.value?.current?.windDirection)
    }

    init {
        currentWeatherModel.observeForever(observer)
    }

    override fun onCleared() {
        super.onCleared()
        currentWeatherModel.removeObserver(observer)
    }
}