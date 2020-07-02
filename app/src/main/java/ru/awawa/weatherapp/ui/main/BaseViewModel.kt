package ru.awawa.weatherapp.ui.main

import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.awawa.weatherapp.repo.WeatherRepo

open class BaseViewModel: ViewModel(), KoinComponent {
    val weatherRepo: WeatherRepo by inject()
}