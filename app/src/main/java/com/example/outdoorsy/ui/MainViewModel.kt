package com.example.outdoorsy.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.outdoorsy.data.repository.RentalRepository

class MainViewModel
@ViewModelInject
constructor(
    private val rentalRepository: RentalRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var liveData = MutableLiveData<String>()

    fun getListings() {
        liveData.postValue("YEE BOYEE")
    }

}