package com.example.outdoorsy.datasource.responsemodels

import com.google.gson.annotations.SerializedName

data class RentalResponse(
    @SerializedName("attributes")
    val attributes: AttributesResponse
)

data class AttributesResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("primary_image_url")
    val primaryImageUrl: String
)