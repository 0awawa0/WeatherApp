package ru.awawa.weatherapp.ui.main.daily

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.LayoutDailyRowBinding
import ru.awawa.weatherapp.repo.retrofit.models.onecall.DailyModel
import ru.awawa.weatherapp.utils.Helpers
import java.text.SimpleDateFormat
import java.util.*


class SevenDaysWeatherAdapter: RecyclerView.Adapter<SevenDaysWeatherAdapter.DailyViewHolder>() {

    var data: Array<DailyModel>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val timeFormatterTime = SimpleDateFormat("dd.MM", Locale.getDefault())
    private val timeFormatterSun = SimpleDateFormat("HH:mm", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val binding: LayoutDailyRowBinding
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_daily_row,
            parent,
            false
        )
        binding = DataBindingUtil.bind(view)!!
        view.tag = binding

        return DailyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        if (data != null) {
            val data = (this.data as Array<DailyModel>)[position]
            val binding = (holder.view.tag as LayoutDailyRowBinding)
            binding.time = timeFormatterTime.format(data.time * 1000)
            binding.sunrise = timeFormatterSun.format(data.sunrise * 1000)
            binding.sunset = timeFormatterSun.format(data.sunset * 1000)
            binding.image = data.weather[0].icon

            if (data.temperature.morning != 0f)
                binding.temperatureMorning = Helpers.formatDegreesToCelsius(data.temperature.morning)
            binding.temperatureDay = Helpers.formatDegreesToCelsius(data.temperature.day)
            binding.temperatureEvening = Helpers.formatDegreesToCelsius(data.temperature.evening)
            binding.temperatureNight = Helpers.formatDegreesToCelsius(data.temperature.night)
            binding.temperatureMin = Helpers.formatDegreesToCelsius(data.temperature.minimal)
            binding.temperatureMax = Helpers.formatDegreesToCelsius(data.temperature.maximal)
            binding.dewPoint = Helpers.formatDegreesToCelsius(data.dewPoint)

            binding.feelsLikeMorning = Helpers.formatDegreesToCelsius(data.feelsLike.morning)
            binding.feelsLikeDay = Helpers.formatDegreesToCelsius(data.feelsLike.day)
            binding.feelsLikeEvening = Helpers.formatDegreesToCelsius(data.feelsLike.evening)
            binding.feelsLikeNight = Helpers.formatDegreesToCelsius(data.feelsLike.night)

            binding.pressure = Helpers.formatPressureToMmHg(data.pressure)
            binding.clouds = data.clouds.toString()

            binding.windSpeed = data.windSpeed.toString()
            binding.windGust = data.windGust.toString()
            binding.windDirection = Helpers.degreesToDirection(data.windDirection)

            binding.humidity = data.humidity.toString()

            binding.rain = data.rain.toString()
            binding.snow = data.snow.toString()
            binding.uvi = data.uvIndex.toString()

        }
    }

    override fun getItemCount(): Int { return data?.size ?: 0 }

    inner class DailyViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    }
}