package com.sampleapp.dashboard.domain.repository

import com.sampleapp.core.repoutils.manageDataSource
import com.sampleapp.dashboard.domain.datasources.local.CategoryLocalDataSource
import com.sampleapp.dashboard.domain.datasources.local.ProductLocalDataSource
import com.sampleapp.dashboard.domain.datasources.remote.CategoryWithProductsRemoteDataSource
import com.sampleapp.dashboard.domain.models.Category
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val remoteDataSource: CategoryWithProductsRemoteDataSource,
    private val categoryPDS: CategoryLocalDataSource,
    private val productPDS: ProductLocalDataSource
) : CategoryRepository {

    override suspend fun getAllCategories() =
        manageDataSource(
            getDataFromPersistence = { categoryPDS.getCategoriesWithProducts()},
            getDataFromServer = { remoteDataSource.getCategoriesWithProducts() },
            updateLocal = {
                clearAll()
                updateLocal(it)
            }
        )

    private suspend fun clearAll() {
        categoryPDS.deleteAll()
        productPDS.deleteAll()
    }

    override suspend fun updateLocal(categoriesWithProducts: List<Category>) {
        categoryPDS.saveCategoriesWithProducts(categoriesWithProducts)
        productPDS.saveProducts(
            categoriesWithProducts.flatMapTo(mutableListOf()) { it.products }
        )
    }

}