package ru.awawa.weatherapp.koin

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.awawa.weatherapp.ui.main.BaseViewModel
import ru.awawa.weatherapp.ui.main.current.CurrentWeatherViewModel
import ru.awawa.weatherapp.ui.main.daily.details.DetailsViewModel
import ru.awawa.weatherapp.ui.main.search.SearchViewModel


val viewModelModule: Module = module {
    viewModel { BaseViewModel() }
    viewModel { CurrentWeatherViewModel() }
    viewModel { SearchViewModel() }
    viewModel { (index: Int) -> DetailsViewModel(index) }
}