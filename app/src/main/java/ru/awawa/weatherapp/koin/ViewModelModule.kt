package ru.awawa.weatherapp.koin

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.awawa.weatherapp.ui.main.CurrentWeatherViewModel
import ru.awawa.weatherapp.ui.search.SearchViewModel


val viewModelModule: Module = module {
    viewModel { CurrentWeatherViewModel() }
    viewModel { SearchViewModel() }
}