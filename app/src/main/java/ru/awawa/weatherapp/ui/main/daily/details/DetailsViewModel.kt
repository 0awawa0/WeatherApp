package ru.awawa.weatherapp.ui.main.daily.details

import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.awawa.weatherapp.repo.WeatherRepo


class DetailsViewModel: ViewModel(), KoinComponent {

    val weatherRepo: WeatherRepo by inject()
}