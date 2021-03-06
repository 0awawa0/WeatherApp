package ru.awawa.weatherapp.ui.main.binding

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ru.awawa.weatherapp.repo.WeatherRepo
import ru.awawa.weatherapp.repo.persistence.City
import ru.awawa.weatherapp.repo.retrofit.models.onecall.DailyModel
import ru.awawa.weatherapp.repo.retrofit.models.onecall.HourlyModel
import ru.awawa.weatherapp.ui.main.BaseViewModel
import ru.awawa.weatherapp.ui.main.current.CurrentWeatherViewModel
import ru.awawa.weatherapp.ui.main.daily.SevenDaysWeatherAdapter
import ru.awawa.weatherapp.ui.main.hourly.TwoDaysWeatherAdapter
import ru.awawa.weatherapp.ui.main.search.SearchAdapter


@BindingAdapter("visibility")
fun setVisibility(v: ProgressBar, result: WeatherRepo.OperationResult?) {
    v.visibility = if (result?.type == WeatherRepo.ResultType.LOADING)
        View.VISIBLE
    else
        View.GONE
}

@BindingAdapter("visibility")
fun setVisibility(v: SwipeRefreshLayout, result: WeatherRepo.OperationResult?) {

    v.visibility = if (result?.type != WeatherRepo.ResultType.LOADING)
        View.VISIBLE
    else
        View.GONE
    v.isRefreshing = false
}

@BindingAdapter("visibility", "weatherRepo")
fun setVisibility(v: TextView, result: WeatherRepo.OperationResult?, weatherRepo: WeatherRepo?) {


    if (result?.type == WeatherRepo.ResultType.ERROR
        && weatherRepo?.oneCallModel?.value?.current == null
    ) {
        v.visibility = View.VISIBLE
        v.text = result.message
    } else {
        v.visibility = View.GONE
        if (result?.type == WeatherRepo.ResultType.ERROR) {
            Snackbar.make(v, result.message, Snackbar.LENGTH_SHORT).show()
        }
    }
}

@BindingAdapter("visibility", "weatherRepo")
fun setVisibility(v: ScrollView, result: WeatherRepo.OperationResult?, weatherRepo: WeatherRepo?) {

    v.visibility = if (result?.type != WeatherRepo.ResultType.SUCCESS
        && weatherRepo?.oneCallModel?.value?.current == null
    )
        View.GONE
    else
        View.VISIBLE
}

@BindingAdapter("url")
fun setUrl(v: ImageView, url: String) {
    Picasso.get().load(url).resize(200, 200).into(v)
}

@BindingAdapter("viewModel")
fun setViewModel(v: SwipeRefreshLayout, viewModel: CurrentWeatherViewModel) {
    v.setOnRefreshListener {
        viewModel.weatherRepo.updateData()
        Handler(Looper.getMainLooper()).postDelayed({ v.isRefreshing = false }, 25000)
    }
}

@BindingAdapter("adapter", "data")
fun setData(v: RecyclerView, adapter: SevenDaysWeatherAdapter?, data: Array<DailyModel>?) {
    v.layoutManager = LinearLayoutManager(v.context)
    val divider = DividerItemDecoration(v.context, (v.layoutManager!! as LinearLayoutManager).orientation)
    v.addItemDecoration(divider)
    v.setHasFixedSize(true)
    v.adapter = adapter
    (v.adapter as SevenDaysWeatherAdapter?)?.data = data
}

@BindingAdapter("image")
fun setImage(v: ImageView, url: String) {
    Picasso.get()
        .load(url)
        .resize(200, 200)
        .centerCrop()
        .into(v)
}

@BindingAdapter("viewModel")
fun setViewModel(v: SwipeRefreshLayout, viewModel: BaseViewModel) {
    v.setOnRefreshListener {
        viewModel.weatherRepo.updateData()
        Handler(Looper.getMainLooper()).postDelayed({ v.isRefreshing = false }, 5000)
    }
}

@BindingAdapter("adapter", "data")
fun setData(v: RecyclerView, adapter: TwoDaysWeatherAdapter?, data: Array<HourlyModel>?) {
    v.layoutManager = LinearLayoutManager(v.context)
    val divider = DividerItemDecoration(v.context, (v.layoutManager!! as LinearLayoutManager).orientation)
    v.addItemDecoration(divider)
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