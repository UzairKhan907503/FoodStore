package com.sampleapp.dashboard.data.local.datasources

import com.sampleapp.dashboard.domain.datasources.local.CategoryLocalDataSource
import com.sampleapp.dashboard.domain.models.Category
import com.sampleapp.dashboard.utils.toDBModel
import com.sampleapp.dashboard.utils.toDomainModel
import com.sampleapp.local.db.daos.CategoryDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryLocalDataSourceImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryLocalDataSource {
    override suspend fun getCategoriesWithProducts(): Flow<List<Category>> {
        return categoryDao.getAllCategoriesWithProducts()
            .distinctUntilChanged()
            .map {
                it.toDomainModel()
            }
    }

    override suspend fun saveCategoriesWithProducts(categories: List<Category>) {
        categoryDao.insert(categories.toDBModel())
    }

    override suspend fun deleteAll() {
        categoryDao.deleteAll()
    }
}