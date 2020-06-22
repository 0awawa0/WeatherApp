package ru.awawa.weatherapp.ui.main.binding

import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.squareup.picasso.Picasso
import ru.awawa.weatherapp.repo.persistence.City
import ru.awawa.weatherapp.repo.retrofit.models.onecall.DailyModel
import ru.awawa.weatherapp.repo.retrofit.models.onecall.HourlyModel
import ru.awawa.weatherapp.repo.retrofit.models.onecall.OneCallModel
import ru.awawa.weatherapp.ui.main.current.CurrentWeatherViewModel
import ru.awawa.weatherapp.ui.main.daily.SevenDaysWeatherAdapter
import ru.awawa.weatherapp.ui.main.daily.SevenDaysWeatherViewModel
import ru.awawa.weatherapp.ui.main.hourly.TwoDaysWeatherAdapter
import ru.awawa.weatherapp.ui.main.hourly.TwoDaysWeatherViewModel
import ru.awawa.weatherapp.ui.main.search.SearchAdapter


@BindingAdapter("visibility")
fun setVisibility(v: ProgressBar, weatherModel: OneCallModel?) {
    v.visibility = if (weatherModel == null) View.VISIBLE else View.GONE
}

@BindingAdapter("visibility")
fun setVisibility(v: SwipeRefreshLayout, weatherModel: OneCallModel?) {
    v.visibility = if (weatherModel == null) View.GONE else View.VISIBLE
    v.isRefreshing = false
}

@BindingAdapter("url")
fun setUrl(v: ImageView, url: String) {
    Picasso.get().load(url).resize(200, 200).into(v)
}

@BindingAdapter("viewModel")
fun setViewModel(v: SwipeRefreshLayout, viewModel: CurrentWeatherViewModel) {
    v.setOnRefreshListener {
        viewModel.weatherRepo.updateData()
        Handler(Looper.getMainLooper()).postDelayed({ v.isRefreshing = false }, 5000)
    }
}

@BindingAdapter("viewModel")
fun setViewModel(v: SwipeRefreshLayout, viewModel: SevenDaysWeatherViewModel) {
    v.setOnRefreshListener {
        viewModel.weatherRepo.updateData()
        Handler(Looper.getMainLooper()).postDelayed({ v.isRefreshing = false }, 5000)
    }
}

@BindingAdapter("adapter", "data")
fun setData(v: RecyclerView, adapter: SevenDaysWeatherAdapter?, data: Array<DailyModel>?) {
    v.layoutManager = LinearLayoutManager(v.context)
    v.setHasFixedSize(true)
    v.adapter = adapter
    (v.adapter as SevenDaysWeatherAdapter?)?.data = data
}

@BindingAdapter("image")
fun setImage(v: ImageView, url: String) {
    Picasso.get()
        .load(url)
        .resize(100, 100)
        .centerCrop()
        .into(v)
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

@BindingAdapter("cities")
fun setCities(listView: ListView, cities: List<City>?) {
    if (cities == null) return
    (listView.adapter as SearchAdapter).updateDataList(cities)
}

@BindingAdapter("queryTextListener")
fun setQueryTextListener(v: SearchView, listener: SearchView.OnQueryTextListener) {
    v.setOnQueryTextListener(listener)
}

@BindingAdapter("adapter")
fun setAdapter(v: ListView, adapter: SearchAdapter) {
    v.adapter = adapter
}