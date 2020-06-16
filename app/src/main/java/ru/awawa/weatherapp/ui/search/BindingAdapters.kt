package ru.awawa.weatherapp.ui.search

import android.widget.ListView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import ru.awawa.weatherapp.persistence.City

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