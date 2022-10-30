package com.berkedursunoglu.kapimda.data.api

import com.berkedursunoglu.kapimda.data.models.Product
import com.berkedursunoglu.kapimda.data.models.ProductItem
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class ApiHelperImp @Inject constructor(private val apiService:ProductApi) :ApiHelper {
    override suspend fun getAllProducts(): Call<Product> {
        return apiService.getAllProducts()
    }
}