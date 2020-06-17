package ru.awawa.weatherapp.ui.main.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.FragmentSearchLayoutBinding
import ru.awawa.weatherapp.databinding.LayoutCityRowBinding
import ru.awawa.weatherapp.ui.main.MainActivity


class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchLayoutBinding
    private val searchViewModel: SearchViewModel by viewModel()
    private val adapter by lazy {
        SearchAdapter(
            emptyList(),
            View.OnClickListener {
                if (activity != null) {
                    val city = DataBindingUtil.bind<LayoutCityRowBinding>(it)?.city
                    if (city != null) {
                        searchViewModel.weatherRepo.city.value = city
                        Navigation.findNavController(binding.root).navigate(R.id.nav_currentWeather)
                    }
                }
            })
    }

    private val queryTextListener by lazy {
        object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchViewModel.doSearch(newText)
                    return true
                }
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchViewModel.doSearch(query)
                    return true
                }
                return false
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search_layout,
            container,
            false
        )
        binding.viewModel = searchViewModel
        binding.searchListener = queryTextListener
        binding.lifecycleOwner = this
        binding.listViewAdapter = adapter

        return binding.root
    }
}