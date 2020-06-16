package ru.awawa.weatherapp.repo.retrofit.models

import com.squareup.moshi.Json

data class MainWeatherDataModel (
    @field:Json(name="temp") val temperature: Float,
    @field:Json(name="feels_like") val feelsLike: Float,
    @field:Json(name="temp_min") val minTemperature: Float,
    @field:Json(name="temp_max") val maxTemperature: Float,
    @field:Json(name="pressure") val pressure: Int,
    @field:Json(name="humidity") val humidity: Int
)