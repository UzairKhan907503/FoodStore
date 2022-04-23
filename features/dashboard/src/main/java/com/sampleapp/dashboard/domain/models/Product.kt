package com.sampleapp.dashboard.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
    val productId: String,
    val categoryId: String,
    val name: String,
    val url: String,
    val description: String,
    val price: String,
    val currency: String
) : Parcelable