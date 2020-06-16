package ru.awawa.weatherapp.repo.retrofit.apis

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.awawa.weatherapp.repo.retrofit.models.CurrentWeatherModel

interface CurrentWeatherApi {

    @GET("weather")
    fun getCurrentWeatherByName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String): Call<CurrentWeatherModel>

    @GET("weather")
    fun getCurrentWeatherById(
        @Query("id") cityId: Long,
        @Query("appid") apiKey: String): Call<CurrentWeatherModel>
}