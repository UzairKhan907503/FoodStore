package com.sampleapp.local.db.models


import androidx.room.Entity

@Entity(primaryKeys = ["productId", "categoryId"])
data class ProductDBModel(
    val productId: String,
    val categoryId: String,
    val url: String,
    val name: String,
    val description: String,
    val currency: String,
    val price: String,
)