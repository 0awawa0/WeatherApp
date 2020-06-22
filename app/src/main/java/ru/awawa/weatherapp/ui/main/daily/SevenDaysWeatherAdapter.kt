package ru.awawa.weatherapp.ui.main.daily

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.awawa.weatherapp.repo.retrofit.models.onecall.DailyModel


class SevenDaysWeatherAdapter: RecyclerView.Adapter<SevenDaysWeatherAdapter.DailyViewHolder>() {

    var data: Array<DailyModel>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class DailyViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    }
}