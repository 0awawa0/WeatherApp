package ru.awawa.weatherapp.ui.main.current

import android.view.View
import android.widget.Button
import androidx.databinding.BindingAdapter

@BindingAdapter("clickListener")
fun setClickListener(v: Button, listener: View.OnClickListener) { v.setOnClickListener(listener) }