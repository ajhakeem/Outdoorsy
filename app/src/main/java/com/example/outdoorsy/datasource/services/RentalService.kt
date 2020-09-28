package com.example.outdoorsy.datasource.services

import com.example.outdoorsy.datasource.responsemodels.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RentalService {


    @GET("rentals")
    fun getListingsByQuery(
        @Query("filter[keywords]") terms: String,
        @Query("page") page: Int,
        @Query("pageOffset") pageOffset: Int): Single<SearchResponse>

}