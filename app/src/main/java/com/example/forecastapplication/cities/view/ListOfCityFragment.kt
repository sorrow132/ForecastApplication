package com.example.forecastapplication.cities.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.forecastapplication.R
import com.example.forecastapplication.WeatherApplication
import com.example.forecastapplication.core.repository.IDBRepository
import com.example.forecastapplication.core.repository.IRepository
import com.example.forecastapplication.cities.viewmodel.IListOfCityViewModelContract
import com.example.forecastapplication.cities.viewmodel.ListOfCityViewModel
import javax.inject.Inject

class ListOfCityFragment : Fragment() {

    private lateinit var viewModel: IListOfCityViewModelContract

    @Inject
    lateinit var repository: IRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as WeatherApplication).component.inject(this)

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return modelClass.getConstructor(IDBRepository::class.java)
                    .newInstance(repository)
            }
        }).get(ListOfCityViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.list_of_city, container, false)

        return view
    }
}