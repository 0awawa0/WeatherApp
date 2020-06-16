package ru.awawa.weatherapp.ui.search

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.LayoutCityRowBinding
import ru.awawa.weatherapp.databinding.SearchActivityBinding
import ru.awawa.weatherapp.utils.CONSTANT_CITY_ID
import ru.awawa.weatherapp.utils.CONSTANT_CITY_NAME

class SearchActivity: AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModel()
    private val adapter by lazy { SearchAdapter(emptyList(), View.OnClickListener {
        this@SearchActivity.setResult(
            Activity.RESULT_OK,
            Intent()
                .putExtra(
                    CONSTANT_CITY_NAME,
                    DataBindingUtil.bind<LayoutCityRowBinding>(it)?.city?.name)
                .putExtra(
                    CONSTANT_CITY_ID,
                    DataBindingUtil.bind<LayoutCityRowBinding>(it)?.city?.id)
        )
        this@SearchActivity.finish()
    }) }

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: SearchActivityBinding = DataBindingUtil.setContentView(
            this,
            R.layout.search_activity
        )
        binding.viewModel = searchViewModel
        binding.searchListener = queryTextListener
        binding.lifecycleOwner = this
        binding.listViewAdapter = adapter

        if (intent.action == Intent.ACTION_SEARCH) {
            intent.getStringExtra(SearchManager.QUERY)?.also {
                    query -> searchViewModel.doSearch(query)
            }
        }
    }
}