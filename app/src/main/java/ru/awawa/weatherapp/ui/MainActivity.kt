package ru.awawa.weatherapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.ActivityMainBinding
import ru.awawa.weatherapp.ui.search.SearchActivity

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

        binding.root.findViewById<Button>(R.id.btSearch).setOnClickListener {
            startActivityForResult(
                Intent(this, SearchActivity::class.java),
                SEARCH_CITY_REQUEST
            )
        }

        currentWeatherViewModel.getCurrentWeather("London")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SEARCH_CITY_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                currentWeatherViewModel.getCurrentWeather(data.getStringExtra("CITY_NAME"))
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
