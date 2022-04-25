package com.sampleapp.dashboard.domain.datasources.local

import com.sampleapp.dashboard.domain.models.Product

interface ProductLocalDataSource {

    suspend fun saveProducts(products: List<Product>)

    suspend fun deleteAll()

}