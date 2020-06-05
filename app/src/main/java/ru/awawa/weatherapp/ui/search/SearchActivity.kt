package ru.awawa.weatherapp.ui.search

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.LayoutCityRowBinding
import ru.awawa.weatherapp.databinding.SearchActivityBinding
import ru.awawa.weatherapp.persistence.City

class SearchActivity: AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModel()
    private val adapter by lazy { SearchAdapter(emptyList(), View.OnClickListener {
        this@SearchActivity.setResult(
            Activity.RESULT_OK,
            Intent().putExtra(
                "CITY_NAME",
                DataBindingUtil.bind<LayoutCityRowBinding>(it)?.city?.name
            )
        )
        this@SearchActivity.finish()
    }) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: SearchActivityBinding = DataBindingUtil.setContentView(
            this,
            R.layout.search_activity
        )
        binding.viewModel = searchViewModel
        binding.lifecycleOwner = this
        binding.root.findViewById<ListView>(R.id.lvCities).adapter = adapter
        binding.root.findViewById<SearchView>(R.id.search).setOnQueryTextListener(object: SearchView.OnQueryTextListener {
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
        })

        if (intent.action == Intent.ACTION_SEARCH) {
            intent.getStringExtra(SearchManager.QUERY)?.also {
                    query -> searchViewModel.doSearch(query)
            }
        }
    }
}