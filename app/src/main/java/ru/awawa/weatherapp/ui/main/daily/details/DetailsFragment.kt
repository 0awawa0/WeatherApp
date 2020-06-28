package ru.awawa.weatherapp.ui.main.daily.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.FragmentDetailsLayoutBinding
import ru.awawa.weatherapp.repo.retrofit.models.onecall.DailyModel
import java.text.SimpleDateFormat
import java.util.*


class DetailsFragment: Fragment() {

    private val detailsViewModel: DetailsViewModel by viewModel()
    private lateinit var binding: FragmentDetailsLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details_layout,
            container,
            false
        )

        binding.lifecycleOwner = this
        binding.viewModel = detailsViewModel
        val formatter = SimpleDateFormat("dd.MM", Locale.getDefault())
        val dailyModel = detailsViewModel
            .weatherRepo
            .oneCallModel
            .value!!
            .daily[requireArguments()["position"] as Int]
        binding.weatherData = dailyModel
        binding.date = formatter.format(dailyModel.time * 1000)

        return binding.root
    }
}