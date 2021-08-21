package com.siriusproject.coshelek.utils

sealed class LoadResult<out T> {

    class Success<out T>(val data: T) : LoadResult<T>()
    class Error(val exception: Throwable) : LoadResult<Nothing>()
    object Loading : LoadResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
        }
    }

    fun <R> map(transform: (T) -> R): LoadResult<R> = when (this) {
        is Success -> Success(transform(data))
        is Error -> this
        is Loading -> this
    }

}