package com.example.outdoorsy.data.repository

import com.example.outdoorsy.datasource.services.RentalService
import com.example.outdoorsy.domain.MainDomainModel
import com.example.outdoorsy.domain.toModels
import io.reactivex.Single
import javax.inject.Inject


class RentalRepository
@Inject constructor(private val service: RentalService) {

    fun getListings(
        queryTerms: String,
        page: Int = 1,
        pageOffset: Int = 0): Single<MainDomainModel> {
        return service.getListingsByQuery(queryTerms, page, pageOffset).map { it.toModels() }
    }

}