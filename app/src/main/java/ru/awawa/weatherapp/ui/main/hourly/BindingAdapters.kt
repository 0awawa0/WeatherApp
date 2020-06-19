package ru.awawa.weatherapp.ui.main.hourly

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.repo.retrofit.models.onecall.HourlyModel
import ru.awawa.weatherapp.repo.retrofit.models.onecall.OneCallModel


@BindingAdapter("visibility")
fun setVisibility(v: ProgressBar, weatherModel: OneCallModel?) {
    v.visibility = if (weatherModel == null) View.VISIBLE else View.GONE
}

@BindingAdapter("visibility")
fun setVisibility(v: SwipeRefreshLayout, weatherModel: OneCallModel?) {
    v.visibility = if (weatherModel == null) View.GONE else View.VISIBLE
    v.isRefreshing = false
}

@BindingAdapter("viewModel")
fun setViewModel(v: SwipeRefreshLayout, viewModel: TwoDaysWeatherViewModel) {
    v.setOnRefreshListener {
        viewModel.weatherRepo.updateData()
        Handler(Looper.getMainLooper()).postDelayed({ v.isRefreshing = false }, 5000)
    }
}

@BindingAdapter("adapter", "data")
fun setData(v: RecyclerView, adapter: TwoDaysWeatherAdapter?, data: Array<HourlyModel>?) {
    v.layoutManager = LinearLayoutManager(v.context)
    v.setHasFixedSize(true)
    v.adapter = adapter
    (v.adapter as TwoDaysWeatherAdapter?)?.data = data
}

@BindingAdapter("image")
fun setImage(v: ImageView, url: String) {
    Picasso.get()
        .load(url)
        .resize(100, 100)
        .centerCrop()
        .into(v)
}