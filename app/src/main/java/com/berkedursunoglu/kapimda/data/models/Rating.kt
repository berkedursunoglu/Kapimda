package com.berkedursunoglu.kapimda.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Rating (
    val count: Int,
    val rate: Double
)