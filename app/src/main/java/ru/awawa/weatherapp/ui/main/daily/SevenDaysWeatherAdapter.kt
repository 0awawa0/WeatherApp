package ru.awawa.weatherapp.ui.main.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
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
            binding.image = data.weather[0].icon

            binding.temperatureMin = Helpers.formatDegreesToCelsius(data.temperature.minimal)
            binding.temperatureMax = Helpers.formatDegreesToCelsius(data.temperature.maximal)

            binding.windSpeed = data.windSpeed.toString()
            binding.windDirection = Helpers.degreesToDirection(data.windDirection)

        }
    }
    override fun getItemCount(): Int { return data?.size ?: 0 }

    inner class DailyViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                val args = Bundle()
                args.putInt("position", adapterPosition)
                Navigation.findNavController(view).navigate(R.id.nav_details, args)
            }
        }
    }
}