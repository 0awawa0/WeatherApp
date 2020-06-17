package ru.awawa.weatherapp.ui.main.current

import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import ru.awawa.weatherapp.repo.retrofit.models.onecall.OneCallModel


@BindingAdapter("visibility")
fun setVisibility(v: ProgressBar, weatherModel: OneCallModel?) {
    v.visibility = if (weatherModel == null) View.VISIBLE else View.GONE
}

@BindingAdapter("visibility")
fun setVisibility(v: LinearLayout, weatherModel: OneCallModel?) {
    v.visibility = if (weatherModel == null) View.GONE else View.VISIBLE
}