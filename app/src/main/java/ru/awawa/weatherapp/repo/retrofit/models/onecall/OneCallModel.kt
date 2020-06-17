package ru.awawa.weatherapp.repo.retrofit.models.onecall

import com.squareup.moshi.Json


data class OneCallModel(
    @field:Json(name="lat") val latitude: Float,
    @field:Json(name="lon") val longitude: Float,
    @field:Json(name="timezone") val timezone: String,
    @field:Json(name="timezone_offset") val timezoneOffset: Int,
    @field:Json(name="current") val current: CurrentModel,
    @field:Json(name="hourly") val hourly: Array<HourlyModel>,
    @field:Json(name="daily") val daily: Array<DailyModel>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OneCallModel

        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false
        if (timezone != other.timezone) return false
        if (timezoneOffset != other.timezoneOffset) return false
        if (current != other.current) return false
        if (!hourly.contentEquals(other.hourly)) return false
        if (!daily.contentEquals(other.daily)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        result = 31 * result + timezone.hashCode()
        result = 31 * result + timezoneOffset
        result = 31 * result + current.hashCode()
        result = 31 * result + hourly.contentHashCode()
        result = 31 * result + daily.contentHashCode()
        return result
    }
}