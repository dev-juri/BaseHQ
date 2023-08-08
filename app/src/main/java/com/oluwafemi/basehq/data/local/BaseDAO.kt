package com.oluwafemi.basehq.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oluwafemi.basehq.utils.Category

@Dao
interface BaseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(vararg products: DbProduct)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(vararg categories: DbCategories)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(vararg cart: DbCart)

    @Query("SELECT * FROM DbProducts")
    fun getProducts(): LiveData<List<DbProduct>>

    @Query("SELECT * FROM DbCategories")
    fun getCategories(): LiveData<List<DbCategories>>

    @Query("SELECT * FROM DbProducts WHERE category=:category")
    fun getFIlteredProducts(category: Category): LiveData<List<DbProduct>>
}