package ru.awawa.weatherapp.repo.retrofit.models.onecall

import com.squareup.moshi.Json


data class SnowModel(
    @field:Json(name="1h") val lastHour: Float
)