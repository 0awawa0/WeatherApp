package ru.awawa.weatherapp.repo.retrofit.models.onecall

import com.squareup.moshi.Json


data class HourlyModel(
    @field:Json(name="dt") val time: Long,
    @field:Json(name="temp") val temperature: Float,
    @field:Json(name="feels_like") val feelsLike: Float,
    @field:Json(name="pressure") val pressure: Float,
    @field:Json(name="humidity") val humidity: Float,
    @field:Json(name="dew_point") val dewPoint: Float,
    @field:Json(name="clouds") val clouds: Int,
    @field:Json(name="visibility") val visibility: Int,
    @field:Json(name="wind_speed") val windSpeed: Float,
    @field:Json(name="wind_gust") val windGust: Float,
    @field:Json(name="wind_deg") val windDirection: Float,
    @field:Json(name="rain") val rain: RainModel,
    @field:Json(name="snow") val snow: SnowModel,
    @field:Json(name="weather") val weather: Array<WeatherModel>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HourlyModel

        if (time != other.time) return false
        if (temperature != other.temperature) return false
        if (feelsLike != other.feelsLike) return false
        if (pressure != other.pressure) return false
        if (humidity != other.humidity) return false
        if (dewPoint != other.dewPoint) return false
        if (clouds != other.clouds) return false
        if (visibility != other.visibility) return false
        if (windSpeed != other.windSpeed) return false
        if (windGust != other.windGust) return false
        if (windDirection != other.windDirection) return false
        if (rain != other.rain) return false
        if (snow != other.snow) return false
        if (!weather.contentEquals(other.weather)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = time.hashCode()
        result = 31 * result + temperature.hashCode()
        result = 31 * result + feelsLike.hashCode()
        result = 31 * result + pressure.hashCode()
        result = 31 * result + humidity.hashCode()
        result = 31 * result + dewPoint.hashCode()
        result = 31 * result + clouds
        result = 31 * result + visibility
        result = 31 * result + windSpeed.hashCode()
        result = 31 * result + windGust.hashCode()
        result = 31 * result + windDirection.hashCode()
        result = 31 * result + rain.hashCode()
        result = 31 * result + snow.hashCode()
        result = 31 * result + weather.contentHashCode()
        return result
    }
}