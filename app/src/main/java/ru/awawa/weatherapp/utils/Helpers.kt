package ru.awawa.weatherapp.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import org.koin.core.KoinComponent
import ru.awawa.weatherapp.persistence.City
import java.io.IOException
import java.nio.charset.Charset


object Helpers: KoinComponent {

    const val TAG = "Helpers"

    fun getCitiesFromJSON(): List<City>? {

        try {
            val context = getKoin().get<Context>()
            val inputStream = context.assets.open("city.list.json")
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()

            val json = String(buffer, Charset.forName("UTF-8"))

            return Gson().fromJson<List<City>>(json, List::class.java)
        } catch (exception: IOException) {
            Log.e(TAG, "Couldn't read cities json")
        } catch (exception: Exception) {
            Log.e(TAG, "Couldn't parse json: $exception")
        }

        return null
    }
}