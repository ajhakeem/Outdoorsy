package com.example.outdoorsy.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.outdoorsy.data.repository.RentalRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel
@ViewModelInject
constructor(
    private val rentalRepository: RentalRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var liveData = MutableLiveData<String>()
    private val disposable = CompositeDisposable()

    fun getListings(queryTerms: String, page: Int, pageOffset: Int) {
        disposable.add(
            rentalRepository.getListings("", 1, 0)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    println()
                }, {
                    println()
                })
        )
    }

}