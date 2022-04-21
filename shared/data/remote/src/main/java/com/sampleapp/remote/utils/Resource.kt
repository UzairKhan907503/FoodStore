package com.sampleapp.remote.utils


/**
 * A data wrapper for getting data with different states
 */

sealed class Resource<out T> {
    data class Valid<T>(val data: T) : Resource<T>()
    data class Invalid<T>(val message: String, val data: T? = null) : Resource<T>()
    data class Loading<T>(val data: T? = null) : Resource<T>()
}