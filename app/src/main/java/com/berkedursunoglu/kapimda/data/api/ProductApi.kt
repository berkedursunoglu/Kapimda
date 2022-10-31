package com.berkedursunoglu.kapimda.data.api

import com.berkedursunoglu.kapimda.data.models.ProductItem
import com.google.android.gms.analytics.ecommerce.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
     fun getAllProducts(): Call<com.berkedursunoglu.kapimda.data.models.Product>

    @GET("/products/category/{category}")
     fun getByCategory(@Path("category") category: String): Call<com.berkedursunoglu.kapimda.data.models.Product>

    @GET("/products/{id}")
     fun getByProductId(@Query("id") productId: String): Call<ProductItem>

}