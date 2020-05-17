package com.example.forecastapplication.cities.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
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
import com.example.forecastapplication.core.db.CityEntity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.list_of_city.*
import javax.inject.Inject

class ListOfCityFragment : Fragment() {
    private lateinit var changeStateToAddNewCity: FloatingActionButton
    private lateinit var changeStateToDefault: Button
    private lateinit var addCity: Button
    private lateinit var addCityField: EditText

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
        changeStateToAddNewCity = view.findViewById(R.id.addCityBtn)
        changeStateToDefault = view.findViewById(R.id.btnCancel)
        addCity = view.findViewById(R.id.btnAdd)
        addCityField = view.findViewById(R.id.etAddCity)

        val recycler: RecyclerView = view.findViewById(R.id.listRecyclerView)

        recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        changeStateToAddNewCity.setOnClickListener {
            viewModel.changeStateToAddNew()
        }

        changeStateToDefault.setOnClickListener {
            viewModel.changeToDefaultState()
        }

        addCity.setOnClickListener {
            viewModel.addNewCity(addCityField.text.toString())
        }

        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                CitiesState.Loading -> {
                    progressBarLoading.visibility = View.VISIBLE
                    textViewLoading.visibility = View.VISIBLE

                    listIsEmpty.visibility = View.GONE
                    addCityBtn.visibility = View.GONE
                    listRecyclerView.visibility = View.GONE
                    textViewError.visibility = View.GONE
                    btnErrorRetry.visibility = View.GONE
                    etAddCity.visibility = View.GONE
                    btnAdd.visibility = View.GONE
                    btnCancel.visibility = View.GONE
                }
                CitiesState.EmptyInfo -> {
                    listIsEmpty.visibility = View.VISIBLE
                    addCityBtn.visibility = View.VISIBLE

                    listRecyclerView.visibility = View.GONE
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

                    listIsEmpty.visibility = View.GONE
                    progressBarLoading.visibility = View.GONE
                    textViewLoading.visibility = View.GONE
                    textViewError.visibility = View.GONE
                    btnErrorRetry.visibility = View.GONE
                    etAddCity.visibility = View.GONE
                    btnAdd.visibility = View.GONE
                    btnCancel.visibility = View.GONE
                    recycler.visibility = View.VISIBLE
                    recycler.adapter = RecyclerViewAdapterListOfCity(
                        infoList = state.city,
                        onSelectCityListener = { selectedCity ->
                            viewModel.selectCity(selectedCity)
                        },
                        onRemoveCityListener = { removeEntity ->
                            viewModel.deleteCity(removeEntity)
                        })
                    val service =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    service.hideSoftInputFromWindow(addCityField.windowToken, 0)
                }

                is CitiesState.AddNew -> {
                    etAddCity.text.clear()

                    etAddCity.visibility = View.VISIBLE
                    btnAdd.visibility = View.VISIBLE
                    btnCancel.visibility = View.VISIBLE

                    listIsEmpty.visibility = View.GONE
                    progressBarLoading.visibility = View.GONE
                    textViewLoading.visibility = View.GONE
                    addCityBtn.visibility = View.GONE
                    listRecyclerView.visibility = View.GONE
                    textViewError.visibility = View.GONE
                    btnErrorRetry.visibility = View.GONE
                }
                is CitiesState.Error -> {
                    textViewError.visibility = View.VISIBLE
                    btnErrorRetry.visibility = View.VISIBLE

                    listIsEmpty.visibility = View.GONE
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
