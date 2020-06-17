package ru.awawa.weatherapp.ui.main.current

import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.awawa.weatherapp.repo.WeatherRepo

class CurrentWeatherViewModel: ViewModel(), KoinComponent {

    val weatherRepo: WeatherRepo by inject()
}