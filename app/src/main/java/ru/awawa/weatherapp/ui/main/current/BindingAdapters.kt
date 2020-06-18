package ru.awawa.weatherapp.ui.main.current

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import ru.awawa.weatherapp.repo.retrofit.models.onecall.OneCallModel


@BindingAdapter("visibility")
fun setVisibility(v: ProgressBar, weatherModel: OneCallModel?) {
    v.visibility = if (weatherModel == null) View.VISIBLE else View.GONE
}

@BindingAdapter("visibility")
fun setVisibility(v: RelativeLayout, weatherModel: OneCallModel?) {
    v.visibility = if (weatherModel == null) View.GONE else View.VISIBLE
}

@BindingAdapter("url")
fun setUrl(v: ImageView, url: String) {
    Picasso.get().load(url).resize(200, 200).into(v)
}