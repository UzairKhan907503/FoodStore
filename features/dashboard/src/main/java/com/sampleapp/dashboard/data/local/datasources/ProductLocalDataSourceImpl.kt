package com.sampleapp.dashboard.data.local.datasources

import com.sampleapp.dashboard.domain.datasources.local.ProductLocalDataSource
import com.sampleapp.dashboard.domain.models.Product
import com.sampleapp.dashboard.utils.toDBModel
import com.sampleapp.local.db.daos.ProductDao
import javax.inject.Inject

class ProductLocalDataSourceImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductLocalDataSource {


    override suspend fun saveProducts(products: List<Product>) {
        productDao.insert(products.toDBModel())
    }

    override suspend fun deleteAll() = productDao.deleteAll()
}