package com.abanoub.newsify.domain.util

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Error(val message: String?) : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()
    object Empty : Resource<Nothing>()
}