package com.oluwafemi.basehq.utils

import com.oluwafemi.basehq.data.domain.DomainCart
import com.oluwafemi.basehq.data.domain.DomainCategory
import com.oluwafemi.basehq.data.domain.DomainProduct
import com.oluwafemi.basehq.data.local.DbCart
import com.oluwafemi.basehq.data.local.DbCategories
import com.oluwafemi.basehq.data.local.DbProduct
import com.oluwafemi.basehq.data.remote.Product

@JvmName("ProductExt1")
fun List<Product>.toDbModel(): Array<DbProduct> {
    return this.map {
        DbProduct(
            id = it.id,
            title = it.title,
            price = it.price,
            description = it.description,
            category = Category.fromValue(it.category),
            image = it.image,
            rating = it.rating.rate
        )
    }.toTypedArray()
}

@JvmName("ProductExt2")
fun List<DbProduct>.fromDbModel(): List<DomainProduct> {
    return map {
        DomainProduct(
            id = it.id,
            title = it.title,
            price = it.price,
            description = it.description,
            category = it.category,
            image = it.image,
            rating = it.rating
        )
    }
}

@JvmName("CategoryExt1")
fun List<DbCategories>.fromDbModel(): List<DomainCategory> {
    return map {
        DomainCategory(
            category = it.category.toString()
        )
    }
}

@JvmName("CategoryExt2")
fun DomainCategory.toDbModel(): DbCategories {
    return DbCategories(
        id = 0,
        category = Category.fromValue(this.category)
    )
}

@JvmName("CartExt1")
fun List<DbCart>.fromDbModel(): List<DomainCart> {
    return map {
        DomainCart(
            productId = it.productId,
            quantity = it.quantity
        )
    }
}