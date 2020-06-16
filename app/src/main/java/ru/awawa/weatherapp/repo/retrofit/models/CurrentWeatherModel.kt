package ru.awawa.weatherapp.repo.retrofit.models

import com.squareup.moshi.Json

data class CurrentWeatherModel(
    @field:Json(name="coord") val coordinates: CoordinateModel,
    @field:Json(name="main") val mainData: MainWeatherDataModel,
    @field:Json(name="wind") val wind: WindModel,
    @field:Json(name="name") val cityName: String
)