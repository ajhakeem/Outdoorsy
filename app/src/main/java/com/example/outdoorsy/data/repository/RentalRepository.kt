package com.example.outdoorsy.data.repository

import com.example.outdoorsy.datasource.responsemodels.SearchResponse
import com.example.outdoorsy.datasource.services.RentalService
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject


class RentalRepository
@Inject constructor(private val service: RentalService) {

    fun getListings(
        queryTerms: String,
        page: Int = 1,
        pageOffset: Int = 0): Single<SearchResponse> {
        return service.getListingsByQuery(queryTerms, page, pageOffset)
    }

}