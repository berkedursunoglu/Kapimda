package com.berkedursunoglu.kapimda.domain.repository

import com.berkedursunoglu.kapimda.data.api.ApiHelper
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiHelper:ApiHelper) {
    suspend fun getAllProducts() = apiHelper.getAllProducts()
    suspend fun getByCategory(category: String) = apiHelper.getByCategory(category)
}