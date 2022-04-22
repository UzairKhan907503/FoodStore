package com.sampleapp.dashboard.data.remote.datasources

import com.sampleapp.dashboard.data.remote.api.ApiService
import com.sampleapp.dashboard.domain.datasources.remote.CategoryWithProductsRemoteDataSource
import com.sampleapp.dashboard.domain.models.Category
import com.sampleapp.dashboard.utils.toDomainModel
import com.sampleapp.remote.datasource.BaseRemoteDataSource
import com.sampleapp.remote.utils.Resource
import com.sampleapp.remote.utils.transform
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryWithProductsRemoteDataSourceImpl @Inject constructor(
    private val apiClient: ApiService
) : CategoryWithProductsRemoteDataSource, BaseRemoteDataSource() {

    override fun getCategoriesWithProducts(): Flow<Resource<List<Category>>> = safeApiCall {
        apiClient.getFoodItems()
    }.map { it ->
        it.transform { it.toDomainModel() }
    }
}