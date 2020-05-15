package com.example.forecastapplication.cities.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forecastapplication.R
import com.example.forecastapplication.WeatherApplication
import com.example.forecastapplication.cities.adapter.RecyclerViewAdapterListOfCity
import com.example.forecastapplication.cities.model.CitiesState
import com.example.forecastapplication.core.repository.IDBRepository
import com.example.forecastapplication.core.repository.IRepository
import com.example.forecastapplication.cities.viewmodel.IListOfCityViewModelContract
import com.example.forecastapplication.cities.viewmodel.ListOfCityViewModel
import kotlinx.android.synthetic.main.list_of_city.*
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

        val recycler: RecyclerView = view.findViewById(R.id.listRecyclerView)

        recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                CitiesState.Loading -> {
                    progressBarLoading.visibility = View.VISIBLE
                    textViewLoading.visibility = View.VISIBLE
                    addCityBtn.visibility = View.GONE
                    listRecyclerView.visibility = View.GONE
                    textViewError.visibility = View.GONE
                    btnErrorRetry.visibility = View.GONE
                    etAddCity.visibility = View.GONE
                    btnAdd.visibility = View.GONE
                    btnCancel.visibility = View.GONE
                }
                CitiesState.EmptyInfo -> {
                    addCityBtn.visibility = View.VISIBLE
                    listRecyclerView.visibility = View.VISIBLE
                    progressBarLoading.visibility = View.GONE
                    textViewLoading.visibility = View.GONE
                    textViewError.visibility = View.GONE
                    btnErrorRetry.visibility = View.GONE
                    etAddCity.visibility = View.GONE
                    btnAdd.visibility = View.GONE
                    btnCancel.visibility = View.GONE
                }
                is CitiesState.LoadInfo -> {
                    addCityBtn.visibility = View.VISIBLE
                    listRecyclerView.visibility = View.VISIBLE
                    progressBarLoading.visibility = View.GONE
                    textViewLoading.visibility = View.GONE
                    textViewError.visibility = View.GONE
                    btnErrorRetry.visibility = View.GONE
                    etAddCity.visibility = View.GONE
                    btnAdd.visibility = View.GONE
                    btnCancel.visibility = View.GONE
                    recycler.visibility = View.VISIBLE
                    recycler.adapter = RecyclerViewAdapterListOfCity(state.city)
                }

                is CitiesState.AddNew -> {
                    addCityBtn.setOnClickListener {
                        etAddCity.visibility = View.VISIBLE
                        btnAdd.visibility = View.VISIBLE
                        btnCancel.visibility = View.VISIBLE

                        progressBarLoading.visibility = View.GONE
                        textViewLoading.visibility = View.GONE
                        addCityBtn.visibility = View.GONE
                        listRecyclerView.visibility = View.GONE
                        textViewError.visibility = View.GONE
                        btnErrorRetry.visibility = View.GONE
                    }
                }
                is CitiesState.Error -> {
                    textViewError.visibility = View.VISIBLE
                    btnErrorRetry.visibility = View.VISIBLE
                    progressBarLoading.visibility = View.GONE
                    textViewLoading.visibility = View.GONE
                    addCityBtn.visibility = View.GONE
                    listRecyclerView.visibility = View.GONE
                    etAddCity.visibility = View.GONE
                    btnAdd.visibility = View.GONE
                    btnCancel.visibility = View.GONE
                }
            }
        })
        return view
    }
}