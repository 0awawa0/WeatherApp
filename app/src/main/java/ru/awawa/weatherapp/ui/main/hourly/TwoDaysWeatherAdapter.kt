package ru.awawa.weatherapp.ui.main.hourly

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.LayoutHourlyRowBinding
import ru.awawa.weatherapp.repo.retrofit.models.onecall.HourlyModel
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
            val data = (this.data as Array<HourlyModel>)[position]
            val binding = (holder.view.tag as LayoutHourlyRowBinding)
            binding.time = timeFormatter.format(data.time * 1000)
            binding.temperature = String.format("%.2f°C", data.temperature - 273)
            binding.image = data.weather[0].icon
        }
    }

    override fun getItemCount(): Int { return data?.size ?: 0 }

    inner class HourlyViewHolder(val view: View): RecyclerView.ViewHolder(view)
}