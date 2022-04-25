package com.sampleapp.dashboard.data

import com.sampleapp.dashboard.domain.models.Category
import com.sampleapp.dashboard.utils.toDomainModel
import com.sampleapp.remote.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.sampleapp.dashboard.data.Response.*
import com.sampleapp.dashboard.utils.toCategoryProductDBModel
import com.sampleapp.local.db.models.CategoryProductsDBModel

fun getCategories(): List<Category> = getCategoriesDTO().toDomainModel()

fun getFlowCategories() = flow { emit(getCategories()) }

const val ERROR_MESSAGE = "Network Error"
fun getCategoriesResourceFlow(resource: Response): Flow<Resource<List<Category>>> =
    flow {
        when (resource) {
            Valid -> { emit(Resource.Valid(getCategories())) }
            Invalid -> { emit(Resource.Invalid(ERROR_MESSAGE)) }
            Loading -> { emit(Resource.Loading()) }
        }
    }

fun getCategoriesDBFlow(): Flow<List<CategoryProductsDBModel>> = flow {
    emit(getCategories().toCategoryProductDBModel())
}

enum class Response {
    Valid,
    Invalid,
    Loading
}