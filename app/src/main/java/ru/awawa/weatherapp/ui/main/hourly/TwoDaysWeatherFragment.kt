package ru.awawa.weatherapp.ui.main.hourly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.FragmentTwoDaysLayoutBinding


class TwoDaysWeatherFragment: Fragment() {

    private val twoDaysWeatherViewModel: TwoDaysWeatherViewModel by viewModel()
    private lateinit var binding: FragmentTwoDaysLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_two_days_layout,
            container,
            false
        )

        binding.lifecycleOwner = this
        binding.viewModel = twoDaysWeatherViewModel
        return binding.root
    }
}