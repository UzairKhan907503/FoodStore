package com.sampleapp.dashboard.ui.productlist

import com.sampleapp.dashboard.domain.models.Category


sealed class FoodItemsListStates {
    object Loading : FoodItemsListStates()
    data class ProductsDetailsFetched(val categoryList : List<Category>) :
        FoodItemsListStates()
}