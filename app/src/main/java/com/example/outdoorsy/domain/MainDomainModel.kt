package com.example.outdoorsy.domain

import com.example.outdoorsy.datasource.responsemodels.SearchResponse

data class MainDomainModel(
    val results: List<RentalDomainModel>,
    var page: Int = 1,
    var isFirstLoad: Boolean = page == 1
)

data class RentalDomainModel(
    val name: String,
    val imageUrl: String
)

fun SearchResponse.toModels(): MainDomainModel {
    val list = mutableListOf<RentalDomainModel>()

    for (rental in data) {
        list.add(RentalDomainModel(
            rental.attributes.name,
            rental.attributes.primaryImageUrl
        ))
    }

    return MainDomainModel(list)
}