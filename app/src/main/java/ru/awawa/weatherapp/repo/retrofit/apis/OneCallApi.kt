package ru.awawa.weatherapp.repo.retrofit.apis

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.awawa.weatherapp.repo.retrofit.models.onecall.OneCallModel


interface OneCallApi {

    @GET("onecall")
    fun getForecast(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("appid") apiKey: String
    ): Call<OneCallModel>
}