package com.oluwafemi.basehq.data.repository

import androidx.lifecycle.LiveData
import com.oluwafemi.basehq.data.domain.DomainCategory
import com.oluwafemi.basehq.data.domain.DomainProduct
import com.oluwafemi.basehq.utils.Category
import com.oluwafemi.basehq.utils.NetworkState

interface Repository {

    suspend fun fetchRemoteProducts(): NetworkState<Any>

    suspend fun fetchRemoteCategories(): NetworkState<Any>

    fun fetchSavedProducts(): LiveData<List<DomainProduct>>

    fun fetchSavedCategories(): LiveData<List<DomainCategory>>

    fun fetchFilteredProducts(category: Category): LiveData<List<DomainProduct>>

    suspend fun fetchProductWithId(key: Long): DomainProduct

}