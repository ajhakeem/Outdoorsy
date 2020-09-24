package com.example.outdoorsy.data.repository

import com.example.outdoorsy.datasource.services.RentalService
import retrofit2.Retrofit
import javax.inject.Inject


class RentalRepository
@Inject constructor(private val service: RentalService) {

    fun getListings() {
        service.getListingsByQuery()
    }

}