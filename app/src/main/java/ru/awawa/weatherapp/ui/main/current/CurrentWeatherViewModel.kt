package ru.awawa.weatherapp.ui.main.current

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

    var temperature = Helpers.formatDegreesToCelsius(currentWeatherModel.value?.current?.temperature)
    var feelsLike = Helpers.formatDegreesToCelsius(currentWeatherModel.value?.current?.feelsLike)

    var pressure = Helpers.formatPressureToMmHg(currentWeatherModel.value?.current?.pressure)
    var dewPoint = Helpers.formatDegreesToCelsius(currentWeatherModel.value?.current?.dewPoint)
    var humidity = currentWeatherModel.value?.current?.humidity?.toInt()

    var clouds = currentWeatherModel.value?.current?.clouds
    var uvIndex = currentWeatherModel.value?.current?.uvIndex

    var visibility = currentWeatherModel.value?.current?.visibility

    var windSpeed = currentWeatherModel.value?.current?.windSpeed
    var windGust = currentWeatherModel.value?.current?.windGust
    var windDirection = Helpers.degreesToDirection(currentWeatherModel.value?.current?.windDirection)

    private val observer = Observer<OneCallModel> {
        temperature = Helpers.formatDegreesToCelsius(currentWeatherModel.value?.current?.temperature)
        feelsLike = Helpers.formatDegreesToCelsius(currentWeatherModel.value?.current?.feelsLike)
        dewPoint = Helpers.formatDegreesToCelsius(currentWeatherModel.value?.current?.dewPoint)
        humidity = currentWeatherModel.value?.current?.humidity?.toInt()
        clouds = currentWeatherModel.value?.current?.clouds
        uvIndex = currentWeatherModel.value?.current?.uvIndex
        visibility = currentWeatherModel.value?.current?.visibility
        windSpeed = currentWeatherModel.value?.current?.windSpeed
        windGust = currentWeatherModel.value?.current?.windGust
        windDirection = Helpers.degreesToDirection(currentWeatherModel.value?.current?.windDirection)
    }

    init {
        currentWeatherModel.observeForever(observer)
    }

    override fun onCleared() {
        super.onCleared()
        currentWeatherModel.removeObserver(observer)
    }
}