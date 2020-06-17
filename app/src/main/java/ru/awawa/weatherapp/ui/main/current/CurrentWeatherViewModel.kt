package ru.awawa.weatherapp.ui.main.current

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.awawa.weatherapp.repo.WeatherRepo
import ru.awawa.weatherapp.repo.retrofit.models.CurrentWeatherModel

class CurrentWeatherViewModel: ViewModel(), KoinComponent {

    private val weatherRepo: WeatherRepo by inject()

    val currentWeather: LiveData<CurrentWeatherModel> = weatherRepo.currentWeatherModel
}