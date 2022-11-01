package com.berkedursunoglu.kapimda.data.api

import com.berkedursunoglu.kapimda.data.models.Product
import com.berkedursunoglu.kapimda.data.models.ProductItem
import retrofit2.Call

interface ApiHelper {
    suspend fun getAllProducts(): Call<Product>

    suspend fun getByCategory(category: String): Call<Product>

}