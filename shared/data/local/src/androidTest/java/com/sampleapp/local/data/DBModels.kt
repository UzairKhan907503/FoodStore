package com.sampleapp.local.data

import com.sampleapp.local.db.models.CategoryDBModel
import com.sampleapp.local.db.models.ProductDBModel

fun getCategories(): List<CategoryDBModel> {
    return listOf(
        CategoryDBModel("35602", "", "Food"),
        CategoryDBModel("36803", "", "Drink")
    )
}

fun getProducts(): List<ProductDBModel> = listOf(
    ProductDBModel("1", "35602", "/Bread.jpg", "bread", "", "Eur", "0.81"),
    ProductDBModel("2", "35602", "/Sandwich.jpg", "sandwich", "", "Eur", "1.12"),
    ProductDBModel("3", "36803", "/Coke.jpg", "Coke", "", "Eur", "0.81"),
    ProductDBModel("4", "36803", "/Fanta.jpg", "Fanta", "", "Eur", "0.81"),
)