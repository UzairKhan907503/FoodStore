package com.sampleapp.core.repoutils

import com.sampleapp.remote.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

inline fun <T> manageDataSource(
    crossinline getDataFromServer: () -> Flow<Resource<T>>,
    crossinline getDataFromPersistence: suspend () -> Flow<T>,
    crossinline updateLocal: suspend (T) -> Unit
): Flow<Resource<T>> = flow {
    getDataFromServer().collect { response ->
        when (response) {
            is Resource.Valid -> {
                updateLocal(response.data)
            }
            is Resource.Invalid -> {
                emit(Resource.Invalid(response.message))
                getDataFromPersistence().collect { emit(Resource.Valid(it)) }
            }
            is Resource.Loading -> {
                emit(Resource.Loading())
            }
        }
    }
}