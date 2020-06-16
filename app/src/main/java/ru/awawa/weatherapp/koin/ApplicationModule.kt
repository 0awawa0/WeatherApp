package ru.awawa.weatherapp.koin

import android.content.Context
import androidx.room.Room
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.awawa.weatherapp.persistence.DATABASE_NAME
import ru.awawa.weatherapp.persistence.Database
import ru.awawa.weatherapp.repo.WeatherRepo
import ru.awawa.weatherapp.repo.retrofit.apis.CurrentWeatherApi
import ru.awawa.weatherapp.repo.retrofit.utils.BASE_URL


val applicationModule: Module = module {
    factory { provideCurrentWeatherApi(get()) }
    single { provideRetrofit() }
    single { provideDatabase(get()) }
    single { provideRepository()}
}

fun provideRepository(): WeatherRepo {
    return WeatherRepo()
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

fun provideCurrentWeatherApi(retrofit: Retrofit): CurrentWeatherApi {
    return retrofit.create(CurrentWeatherApi::class.java)
}

fun provideDatabase(context: Context): Database {
    return Room.databaseBuilder(context, Database::class.java, DATABASE_NAME)
        .createFromAsset("cities.db")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}