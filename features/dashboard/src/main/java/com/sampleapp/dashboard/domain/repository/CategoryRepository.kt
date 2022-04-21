package com.sampleapp.dashboard.domain.repository

import com.sampleapp.dashboard.domain.models.Category
import com.sampleapp.remote.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getAllCategories(): Flow<Resource<List<Category>>>

    suspend fun updateLocal(categoriesWithProducts: List<Category>)
}