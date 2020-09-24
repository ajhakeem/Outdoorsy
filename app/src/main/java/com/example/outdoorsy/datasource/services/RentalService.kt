package com.example.outdoorsy.datasource.services

import retrofit2.http.GET

interface RentalService {


    @GET("")
    fun getListingsByQuery() {

    }

}