package com.sampleapp.dashboard.domain.datasources.remote

import com.sampleapp.dashboard.domain.models.Category
import com.sampleapp.remote.utils.Resource
import kotlinx.coroutines.flow.Flow


interface CategoryWithProductsRemoteDataSource {

    /**
     * Returns all categories and their respective products from server
     */
    fun getCategoriesWithProducts(): Flow<Resource<List<Category>>>
}