package com.example.outdoorsy.datasource.responsemodels

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("data")
    val data: List<RentalResponse>
)