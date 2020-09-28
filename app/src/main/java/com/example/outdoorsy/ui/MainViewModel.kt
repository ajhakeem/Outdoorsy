package com.example.outdoorsy.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.outdoorsy.data.repository.RentalRepository
import com.example.outdoorsy.domain.MainDomainModel
import com.example.outdoorsy.extension.setError
import com.example.outdoorsy.extension.setLoading
import com.example.outdoorsy.extension.setSuccess
import com.example.outdoorsy.state.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel
@ViewModelInject
constructor(
    private val rentalRepository: RentalRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var liveData = MutableLiveData<Resource<MainDomainModel>>()
    private val disposable = CompositeDisposable()

    fun getListings(queryTerms: String, page: Int, pageOffset: Int) {
        disposable.add(
            rentalRepository.getListings(queryTerms, page, pageOffset)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe { liveData.setLoading() }
                .subscribe({
                    liveData.setSuccess(it)
                }, {
                    liveData.setError(it.message)
                })
        )
    }

}