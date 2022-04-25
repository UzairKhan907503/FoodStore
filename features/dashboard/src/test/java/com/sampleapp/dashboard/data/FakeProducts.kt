package com.sampleapp.dashboard.data

import com.sampleapp.dashboard.data.remote.models.CategoryDTO
import com.sampleapp.dashboard.data.remote.models.ProductDTO
import com.sampleapp.dashboard.data.remote.models.ProductDTO.*


fun getCategoriesDTO(): List<CategoryDTO> {
    val product1 = ProductDTO(
        id = "1",
        categoryId = "35602",
        name = "bread",
        url = "/Bread.jpg",
        description = "",
        salePrice = SalePriceDTO("0.81", "Eur")
    )
    val product2 = ProductDTO(
        id = "2",
        categoryId = "35609",
        name = "Sandwich",
        url = "/Sandwich.jpg",
        description = "",
        salePrice = SalePriceDTO("0.21", "Eur")
    )
    val product3 = ProductDTO(
        id = "3",
        categoryId = "36803",
        name = "Cola",
        url = "/Cola.jpg",
        description = "",
        salePrice = SalePriceDTO("0.81", "Eur")
    )
    val product4 = ProductDTO(
        id = "4",
        categoryId = "36890",
        name = "Fanta",
        url = "/Fanta.jpg",
        description = "",
        salePrice = SalePriceDTO("0.81", "Eur")
    )
    val foodProducts = listOf(product1, product2)
    val drinkProducts = listOf(product3, product4)

    return listOf(
        CategoryDTO(id = "1", name = "Food", "", foodProducts),
        CategoryDTO(id = "2", name = "Drink", description = "", products = drinkProducts)
    )

}