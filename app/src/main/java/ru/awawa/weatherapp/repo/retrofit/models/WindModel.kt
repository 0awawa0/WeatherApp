package ru.awawa.weatherapp.repo.retrofit.models

import com.squareup.moshi.Json

data class WindModel (
    @field:Json(name="speed") val speed: Float,
    @field:Json(name="deg") val direction: Int
)