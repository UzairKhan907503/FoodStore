package com.sampleapp.dashboard.domain.models

data class Category(
    val categoryId: String,
    val name: String,
    val description: String,
    val products: List<Product>
)