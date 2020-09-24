package com.example.outdoorsy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.outdoorsy.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


//Replaces need to declare main activity as something that is injected into component
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.liveData.observe(this, Observer {
            tvText.text = it
        })

        mainViewModel.getListings()
    }
}