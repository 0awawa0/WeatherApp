package ru.awawa.weatherapp.repo.retrofit.models.onecall

import com.squareup.moshi.Json


data class FeelsLikeModel(
    @field:Json(name="morn") val morning: Float,
    @field:Json(name="day") val day: Float,
    @field:Json(name="eve") val evening: Float,
    @field:Json(name="night") val night: Float
)