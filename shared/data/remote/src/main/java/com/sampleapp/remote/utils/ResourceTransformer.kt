package com.sampleapp.remote.utils

/**
 * Transforms a resource to another type given to it my using
 * @param converter
 */

fun <T, R> Resource<T>.transform(
    converter: ((value: T) -> R)
): Resource<R> = when (this) {
    is Resource.Valid -> Resource.Valid(converter.invoke(data))
    is Resource.Invalid -> Resource.Invalid(message, data?.let { converter.invoke(it) })
    is Resource.Loading -> Resource.Loading(data?.let { converter.invoke(it) })
}


/**
 * get data from resource
 */

fun <T> Resource<T>.getDataOrNull() = when (this) {
    is Resource.Valid -> data
    is Resource.Invalid -> data
    is Resource.Loading -> data
}