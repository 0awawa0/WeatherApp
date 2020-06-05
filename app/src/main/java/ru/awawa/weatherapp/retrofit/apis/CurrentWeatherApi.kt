package ru.awawa.weatherapp.retrofit.apis

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.awawa.weatherapp.retrofit.models.CurrentWeatherModel

interface CurrentWeatherApi {

    @GET("weather")
    fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String): Call<CurrentWeatherModel>
}