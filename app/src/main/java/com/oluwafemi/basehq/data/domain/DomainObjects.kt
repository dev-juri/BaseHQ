package com.oluwafemi.basehq.data.domain

import com.oluwafemi.basehq.utils.Category

data class DomainProduct(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: Category,
    val image: String,
    val rating: Double
)

data class DomainCategory(
    val category: Category
)

data class DomainCart(
    val productId: Long,
    val quantity: Long
)