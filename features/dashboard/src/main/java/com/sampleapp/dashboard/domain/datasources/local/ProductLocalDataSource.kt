package com.sampleapp.dashboard.domain.datasources.local

import com.sampleapp.dashboard.domain.models.Product
import kotlinx.coroutines.flow.StateFlow

interface ProductLocalDataSource {

    suspend fun saveProducts(products: List<Product>)

    suspend fun deleteAll()

}