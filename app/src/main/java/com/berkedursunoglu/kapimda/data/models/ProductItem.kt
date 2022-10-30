package com.berkedursunoglu.kapimda.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class ProductItem (
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)