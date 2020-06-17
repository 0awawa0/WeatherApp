package ru.awawa.weatherapp.ui.main.current

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.FragmentMainLayoutBinding


class CurrentWeather: Fragment() {

    private val currentWeatherViewModel: CurrentWeatherViewModel by viewModel()
    private lateinit var binding: FragmentMainLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main_layout,
            container,
            false
        )

        binding.lifecycleOwner = this
        binding.viewModel = currentWeatherViewModel
        return binding.root
    }
}