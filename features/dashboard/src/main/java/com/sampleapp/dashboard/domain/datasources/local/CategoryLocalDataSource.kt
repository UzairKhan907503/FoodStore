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

    suspend fun deleteAll()

}