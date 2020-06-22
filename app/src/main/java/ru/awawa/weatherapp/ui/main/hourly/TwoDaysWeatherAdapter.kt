package ru.awawa.weatherapp.ui.main.hourly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.LayoutHourlyRowBinding
import ru.awawa.weatherapp.repo.retrofit.models.onecall.HourlyModel
import ru.awawa.weatherapp.utils.Helpers
import java.text.SimpleDateFormat
import java.util.*


class TwoDaysWeatherAdapter: RecyclerView.Adapter<TwoDaysWeatherAdapter.HourlyViewHolder>() {

    var data: Array<HourlyModel>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val timeFormatter = SimpleDateFormat("dd.MM HH:mm", Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val binding: LayoutHourlyRowBinding
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.layout_hourly_row,
            parent,
            false
        )
        binding = DataBindingUtil.bind(view)!!
        view.tag = binding

        return HourlyViewHolder(view)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        if (data != null) {
            (holder.view as HorizontalScrollView).smoothScrollTo(0, 0)
            val data = (this.data as Array<HourlyModel>)[position]
            val binding = (holder.view.tag as LayoutHourlyRowBinding)
            binding.time = timeFormatter.format(data.time * 1000)
            binding.temperature = String.format("%.2f", data.temperature - 273)
            binding.image = data.weather[0].icon
            binding.feelsLike = String.format("%.2f", data.feelsLike - 273)
            binding.humidity = data.humidity.toString()
            binding.pressure = data.pressure.toString()
            binding.dewPoint = String.format("%.2f", data.dewPoint - 273)
            binding.pressure = String.format("%.2f", data.pressure * 0.750062)
            binding.clouds = data.clouds.toString()
            binding.windSpeed = data.windSpeed.toString()
            binding.windGust = data.windGust.toString()
            binding.windDirection = Helpers.degreesToDirection(data.windDirection)
        }
    }

    override fun getItemCount(): Int { return data?.size ?: 0 }

    inner class HourlyViewHolder(val view: View): RecyclerView.ViewHolder(view)
}