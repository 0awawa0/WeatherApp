package ru.awawa.weatherapp.ui.main.hourly

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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