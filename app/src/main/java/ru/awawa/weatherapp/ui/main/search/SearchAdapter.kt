package ru.awawa.weatherapp.ui.main.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.LayoutCityRowBinding
import ru.awawa.weatherapp.repo.persistence.City


class SearchAdapter(private var dataList: List<City>,
                    private val itemClickListener: View.OnClickListener
): BaseAdapter() {

    fun updateDataList(dataList: List<City>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding: LayoutCityRowBinding
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(parent?.context).inflate(
                R.layout.layout_city_row,
                parent,
                false
            )
            binding = DataBindingUtil.bind(view)!!
            view.tag = binding
        } else {
            binding = convertView.tag as LayoutCityRowBinding
        }
        binding.city = dataList[position]
        binding.root.setOnClickListener(itemClickListener)
        return binding.root
    }

    override fun getItem(position: Int): Any { return dataList[position] }

    override fun getItemId(position: Int): Long { return 0L }

    override fun getCount(): Int { return dataList.size }
}