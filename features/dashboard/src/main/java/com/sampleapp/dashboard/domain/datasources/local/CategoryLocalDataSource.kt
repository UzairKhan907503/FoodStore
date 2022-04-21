package com.sampleapp.dashboard.domain.datasources.local

import com.sampleapp.dashboard.domain.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryLocalDataSource {
    /**
     *  Returns list of categories
     */
    suspend fun getCategoriesWithProducts(): Flow<List<Category>>

    /**
     * persist categories
     */
    suspend fun saveCategoriesWithProducts(categories: List<Category>)

    /**
     *  Returns a list of categories containing query
     */
    suspend fun searchProducts(query: String): Flow<List<Category>>

    suspend fun deleteAll()

}