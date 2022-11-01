package com.berkedursunoglu.kapimda.utils

import com.berkedursunoglu.kapimda.data.models.BasketModel

interface BasketItemOnClickListener{
    fun onClick(item: BasketModel, itemCount: Int)
}