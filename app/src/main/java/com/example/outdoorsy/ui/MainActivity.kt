package com.example.outdoorsy.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.outdoorsy.R
import com.example.outdoorsy.domain.MainDomainModel
import com.example.outdoorsy.extension.gone
import com.example.outdoorsy.extension.visible
import com.example.outdoorsy.state.Resource
import com.example.outdoorsy.state.ResourceState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


//Replaces need to declare main activity as something that is injected into component
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnBottomReachedListener {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var listingsAdapter: ListingsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        rvListings.layoutManager = LinearLayoutManager(this)
        listingsAdapter = ListingsAdapter(listOf(), this)
        rvListings.adapter = listingsAdapter

        mainViewModel.liveData.observe(this, Observer {
            updateUI(it)
        })

        if (mainViewModel.liveData.value?.data != null) {
            layoutEmptyState.gone()
        }

        svSearch.setOnClickListener{
            svSearch.isIconified = false
        }

        svSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { s ->
                    mainViewModel.getListings(s, 1, 0)
                }

                return false
            }
        })

    }

    private fun updateUI(resource: Resource<MainDomainModel>) {
        when (resource.state) {
            ResourceState.LOADING -> {
                pbLoading.visible()
                layoutErrorState.gone()
                layoutEmptyState.gone()
            }
            ResourceState.SUCCESS -> {
                pbLoading.gone()
                gSearchResults.visible()
                resource.data?.let { model ->
                    if (model.isFirstLoad) {
                        listingsAdapter.addItems(model.results)
                    } else {
                        listingsAdapter.updateItems(model.results)
                    }
                }
            }
            ResourceState.ERROR -> {
                pbLoading.gone()
                gSearchResults.gone()
                layoutErrorState.visible()
            }
        }
    }

    fun onSearchBarClicked(v : View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(svSearch, 0)
    }

    override fun onBottomReached(pos: Int) {

    }
}