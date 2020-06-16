package ru.awawa.weatherapp.ui.main.current

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.FragmentMainLayoutBinding
import ru.awawa.weatherapp.ui.search.SearchActivity
import ru.awawa.weatherapp.utils.CONSTANT_CITY_ID


class CurrentWeather: Fragment() {

    companion object {
        const val SEARCH_CITY_REQUEST = 0x100
    }

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
        binding.buttonClickListener = View.OnClickListener {
            startActivityForResult(
                Intent(requireContext(), SearchActivity::class.java),
                SEARCH_CITY_REQUEST
            )
        }
        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SEARCH_CITY_REQUEST && resultCode == Activity.RESULT_OK) {
            val cityId = data?.getLongExtra(CONSTANT_CITY_ID, -1)
            if (cityId != null) { currentWeatherViewModel.updateWeather(cityId) }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}