package ru.awawa.weatherapp.ui.main.daily.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.awawa.weatherapp.R
import ru.awawa.weatherapp.databinding.FragmentDetailsLayoutBinding
import ru.awawa.weatherapp.repo.retrofit.models.onecall.DailyModel
import java.text.SimpleDateFormat
import java.util.*


class DetailsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val index = arguments?.get("position")!! as Int
        val detailsViewModel: DetailsViewModel by viewModel { parametersOf(index) }

        val binding: FragmentDetailsLayoutBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details_layout,
            container,
            false
        )

        binding.lifecycleOwner = this
        binding.viewModel = detailsViewModel

        return binding.root
    }
}