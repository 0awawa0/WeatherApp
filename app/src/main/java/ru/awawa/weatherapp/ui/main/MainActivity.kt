package ru.awawa.weatherapp.ui.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.ActivityMainBinding
import ru.awawa.weatherapp.ui.search.SearchActivity
import ru.awawa.weatherapp.utils.CONSTANT_CITY_ID
import ru.awawa.weatherapp.utils.CONSTANT_CITY_NAME

class MainActivity : AppCompatActivity() {

    companion object {
        const val SEARCH_CITY_REQUEST = 0x100
    }
    private val currentWeatherViewModel: CurrentWeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        binding.viewModel = currentWeatherViewModel
        binding.lifecycleOwner = this
        binding.buttonClickListener = View.OnClickListener {
            startActivityForResult(
            Intent(this, SearchActivity::class.java),
            SEARCH_CITY_REQUEST)
        }

        currentWeatherViewModel.getCurrentWeatherByName("London")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SEARCH_CITY_REQUEST && resultCode == Activity.RESULT_OK) {
            val cityId = data?.getLongExtra(CONSTANT_CITY_ID, -1)
            if (cityId != null) { currentWeatherViewModel.getCurrentWeatherById(cityId) }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
