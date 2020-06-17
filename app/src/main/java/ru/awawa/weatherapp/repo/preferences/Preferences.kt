package ru.awawa.weatherapp.repo.preferences

import android.content.Context
import android.content.SharedPreferences

const val PREFERENCES_FILE = "ru.awawa.weatherapp.preferences"

class Preferences(context: Context) {

    private val appPreferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCES_FILE,
        Context.MODE_PRIVATE
    )

    var currentCity: Long = appPreferences.getLong("CITY", 2643743)
        set(value) {
            appPreferences.edit().putLong("CITY", value).apply()
            field = value
        }
}