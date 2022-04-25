package com.sampleapp.dashboard.data.local

import com.sampleapp.dashboard.data.remote.models.CategoryDTO
import com.sampleapp.dashboard.data.remote.models.ProductDTO
import com.sampleapp.dashboard.data.remote.models.ProductDTO.*


fun getCategoriesDTO(): List<CategoryDTO> {
    val product1 = ProductDTO(
        "1", "35602", "bread", "/Bread.jpg",
        "", SalePriceDTO("0.81", "Eur")
    )
    val product2 = ProductDTO(
        "2", "35602", "Sandwich", "/Sandwich.jpg",
        "", SalePriceDTO("0.21", "Eur")
    )
    val product3 = ProductDTO(
        "3", "36803", "Cola", "/Cola.jpg",
        "", SalePriceDTO("0.81", "Eur")
    )
    val product4 = ProductDTO(
        "4", "36803", "Fanta", "/Fanta.jpg",
        "", SalePriceDTO("0.81", "Eur")
    )
    val foodProducts = listOf(product1, product2)
    val drinkProducts = listOf(product3, product4)

    return listOf(
        CategoryDTO(id = "35602", name = "Food", "", foodProducts),
        CategoryDTO(id = "36803", name = "Drink", description = "", products = drinkProducts)
    )

}