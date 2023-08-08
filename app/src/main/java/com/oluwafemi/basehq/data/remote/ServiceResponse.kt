package com.oluwafemi.basehq.data.remote

import com.oluwafemi.basehq.utils.Category

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating
)

data class RemoteCategory(
    val category: Category
)

data class Rating(
    val rate: Double,
    val count: Long
)