package ru.awawa.weatherapp.ui.main.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.FragmentSevenDaysLayoutBinding
import ru.awawa.weatherapp.ui.main.BaseViewModel


class SevenDaysWeatherFragment: Fragment() {

    private val sevenDaysViewModel: BaseViewModel by viewModel()
    private lateinit var binding: FragmentSevenDaysLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_seven_days_layout,
            container,
            false
        )

        binding.lifecycleOwner = this
        binding.viewModel = sevenDaysViewModel
        binding.adapter = SevenDaysWeatherAdapter()

        return binding.root
    }
}