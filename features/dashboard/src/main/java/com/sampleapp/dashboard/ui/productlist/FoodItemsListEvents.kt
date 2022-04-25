package com.sampleapp.dashboard.ui.productlist

import com.sampleapp.dashboard.domain.models.Product

sealed class FoodItemsListEvents {
    data class ProductSelected(val product: Product) : FoodItemsListEvents()
}