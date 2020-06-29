package ru.awawa.weatherapp.ui.main.daily.details

import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.awawa.weatherapp.repo.WeatherRepo
import ru.awawa.weatherapp.utils.Helpers
import java.text.SimpleDateFormat
import java.util.*


class DetailsViewModel(index: Int): ViewModel(), KoinComponent {

    val weatherRepo: WeatherRepo by inject()
    val data = weatherRepo.oneCallModel.value?.daily?.get(index)
    val date: String = SimpleDateFormat("dd.MM", Locale.getDefault())
        .format(data?.time?.times(1000))

    val sunrise: String = SimpleDateFormat("HH:mm", Locale.getDefault())
        .format(data?.sunrise?.times(1000))
    val sunset: String = SimpleDateFormat("HH:mm", Locale.getDefault())
        .format(data?.sunset?.times(1000))

    val morning = data?.temperature?.morning?.minus(273)?.toInt()
    val day = data?.temperature?.day?.minus(273)?.toInt()
    val evening = data?.temperature?.evening?.minus(273)?.toInt()
    val night = data?.temperature?.night?.minus(273)?.toInt()

    val feelsLikeMorning = data?.feelsLike?.morning?.minus(273)?.toInt()
    val feelsLikeDay = data?.feelsLike?.day?.minus(273)?.toInt()
    val feelsLikeEvening = data?.feelsLike?.evening?.minus(273)?.toInt()
    val feelsLikeNight = data?.feelsLike?.night?.minus(273)?.toInt()

    val windSpeed = data?.windSpeed
    val windGust = data?.windGust
    val windDirection = Helpers.degreesToDirection(data?.windDirection)

    val pressure = Helpers.formatPressureToMmHg(data?.pressure)
    val humidity = data?.humidity?.toInt()
    val dewPoint = data?.dewPoint?.minus(273)
    val visibility = data?.visibility

    val clouds = data?.clouds
    val uvIndex = data?.uvIndex?.toInt()

    val rain = data?.rain?.toInt()
    val snow = data?.snow?.toInt()
}