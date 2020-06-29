package ru.awawa.weatherapp.utils

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import org.koin.core.KoinComponent
import ru.awawa.weatherapp.repo.persistence.City
import java.io.IOException
import java.nio.charset.Charset
import kotlin.math.abs


object Helpers: KoinComponent {

    const val TAG = "Helpers"

    val DIRECTIONS = hashMapOf(
        Pair("N", 0),
        Pair("NE", 45),
        Pair("E", 90),
        Pair("SE", 135),
        Pair("S", 180),
        Pair("SW", 225),
        Pair("W", 270),
        Pair("NW", 315)
    )

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

    fun degreesToDirection(degrees: Float?): String {
        if (degrees == null) return ""

        var currDirection = "N"
        var minDiff = 361f
        for (direction in DIRECTIONS.keys) {
            if (abs(degrees - DIRECTIONS[direction]!!) < minDiff) {
                currDirection = direction
                minDiff = abs(degrees - DIRECTIONS[direction]!!)
                if (minDiff == 0f) return currDirection
            }
        }
        return currDirection
    }

    fun formatDegreesToCelsius(degrees: Float): String {
        return String.format("%.2f", degrees - 273)
    }

    fun formatPressureToMmHg(pressure: Float?): String {
        return String.format("%.2f", pressure?.times(0.750062))
    }
}