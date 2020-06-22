package ru.awawa.weatherapp.koin

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.awawa.weatherapp.ui.main.current.CurrentWeatherViewModel
import ru.awawa.weatherapp.ui.main.daily.SevenDaysWeatherViewModel
import ru.awawa.weatherapp.ui.main.hourly.TwoDaysWeatherViewModel
import ru.awawa.weatherapp.ui.main.search.SearchViewModel


val viewModelModule: Module = module {
    viewModel { CurrentWeatherViewModel() }
    viewModel { SearchViewModel() }
    viewModel { TwoDaysWeatherViewModel() }
    viewModel { SevenDaysWeatherViewModel() }
}