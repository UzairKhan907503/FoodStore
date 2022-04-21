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