package com.oluwafemi.basehq.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.oluwafemi.basehq.utils.Category

@Entity(tableName = "DbProducts")
data class DbProduct(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: Category,
    val image: String,
    val rating: Double
)

@Entity(tableName = "DbCart")
data class DbCart(
    @PrimaryKey(autoGenerate = false) val productId: Long,
    val quantity: Long
)

@Entity(tableName = "DbCategories")
data class DbCategories(
    val id: Int,
    @PrimaryKey(autoGenerate = false) val category: Category
)