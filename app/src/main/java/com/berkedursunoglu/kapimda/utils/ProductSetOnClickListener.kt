package com.berkedursunoglu.kapimda.utils

import com.berkedursunoglu.kapimda.data.models.ProductItem

interface ProductSetOnClickListener{
    fun goToDetail(item: ProductItem)
}