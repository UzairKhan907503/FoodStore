package com.sampleapp.dashboard.ui.productdetails

import com.sampleapp.dashboard.domain.models.Product


sealed class ProductDetailsStates{
    object Idle : ProductDetailsStates()
    data class ProductDetailsReceived(val model : Product) : ProductDetailsStates()
}
