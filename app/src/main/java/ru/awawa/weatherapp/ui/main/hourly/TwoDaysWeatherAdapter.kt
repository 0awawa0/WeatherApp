package ru.awawa.weatherapp.ui.main.hourly

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.awawa.weatherapp.repo.retrofit.models.onecall.HourlyModel


class TwoDaysWeatherAdapter: RecyclerView.Adapter<TwoDaysWeatherAdapter.HourlyViewHolder>() {

    private var data: Array<HourlyModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        TODO()
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        TODO()
    }

    override fun getItemCount(): Int { return data?.size ?: 0 }

    inner class HourlyViewHolder(private val view: View): RecyclerView.ViewHolder(view)
}