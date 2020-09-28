package com.example.outdoorsy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
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
        setContentView(R.layout.activity_main)

        rvListings.layoutManager = LinearLayoutManager(this)
        listingsAdapter = ListingsAdapter(listOf(), this)
        rvListings.adapter = listingsAdapter

        mainViewModel.liveData.observe(this, Observer {
            updateUI(it)
        })

        mainViewModel.getListings("", 1, 0)
    }

    private fun updateUI(resource: Resource<MainDomainModel>) {
        when (resource.state) {
            ResourceState.LOADING -> {
                pbLoading.visible()
                tvError.gone()
            }
            ResourceState.SUCCESS -> {
                pbLoading.gone()
                tvError.gone()
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
                tvError.visible()
            }
        }
    }

    override fun onBottomReached(pos: Int) {

    }
}