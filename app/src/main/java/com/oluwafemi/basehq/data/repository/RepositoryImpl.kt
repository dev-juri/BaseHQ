package com.oluwafemi.basehq.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.oluwafemi.basehq.data.domain.DomainCategory
import com.oluwafemi.basehq.data.domain.DomainProduct
import com.oluwafemi.basehq.data.local.BaseDAO
import com.oluwafemi.basehq.data.remote.BaseService
import com.oluwafemi.basehq.data.remote.Product
import com.oluwafemi.basehq.data.remote.RemoteCategory
import com.oluwafemi.basehq.utils.Category
import com.oluwafemi.basehq.utils.NetworkState
import com.oluwafemi.basehq.utils.fromDbModel
import com.oluwafemi.basehq.utils.toDbModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remote: BaseService, private val local: BaseDAO,
    private val dispatcher: CoroutineDispatcher
) : Repository {
    override suspend fun fetchRemoteProducts(): NetworkState<Any> = withContext(dispatcher) {
        return@withContext try {
            val result = remote.getAllProducts()
            if (result.isSuccessful) {
                val response = result.body() as List<Product>
                if (response.isNotEmpty()) {
                    local.insertProducts(*response.toDbModel())
                }
                NetworkState.Success(response)
            } else {
                NetworkState.Error("Something went wrong. Please try again")
            }
        } catch (exception: Exception) {
            NetworkState.Error("Internal Server Error. Please, try again")
        }
    }

    override suspend fun fetchRemoteCategories() = withContext(dispatcher) {
        return@withContext try {
            val result = remote.getCategories()
            if (result.isSuccessful) {
                val response = result.body()
                if (response?.size!! > 0) {
                    response.forEach {
                        local.insertCategories(RemoteCategory(Category.fromValue(it)).toDbModel())
                    }
                }
                NetworkState.Success(response)
            } else {
                NetworkState.Error("Something went wrong. Please try again")
            }
        } catch (exception: Exception) {
            NetworkState.Error("Internal Server Error. Please, try again")
        }
    }

    override fun fetchSavedProducts(): LiveData<List<DomainProduct>> = local.getProducts().map {
        it.fromDbModel()
    }

    override fun fetchSavedCategories(): LiveData<List<DomainCategory>> =
        local.getCategories().map {
            it.fromDbModel()
        }

    override fun fetchFilteredProducts(category: Category): LiveData<List<DomainProduct>> =
        local.getFIlteredProducts(category).map {
            it.fromDbModel()
        }
}