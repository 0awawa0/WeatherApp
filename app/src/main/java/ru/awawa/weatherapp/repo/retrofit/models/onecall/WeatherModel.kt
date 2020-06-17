package ru.awawa.weatherapp.repo.retrofit.models.onecall

import com.squareup.moshi.Json


data class WeatherModel(
    @field:Json(name="id") val id: Long,
    @field:Json(name="main") val main: String,
    @field:Json(name="description") val description: String,
    @field:Json(name="icon") val icon: String
)