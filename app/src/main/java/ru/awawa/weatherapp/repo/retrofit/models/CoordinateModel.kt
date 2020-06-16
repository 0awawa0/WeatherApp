package ru.awawa.weatherapp.repo.retrofit.models

import com.squareup.moshi.Json

data class CoordinateModel (
    @field:Json(name="lon") val longitude: Float,
    @field:Json(name="lat") val latitude: Float
)