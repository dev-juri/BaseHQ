package com.oluwafemi.basehq.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface BaseService {

    @GET("/products")
    suspend fun getAllProducts(): Response<List<Product>>

    @GET("/products/categories")
    suspend fun getCategories(): Response<List<String>>
}