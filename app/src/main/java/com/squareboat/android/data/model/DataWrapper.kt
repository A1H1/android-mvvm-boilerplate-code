package com.squareboat.android.data.model

data class DataWrapper<T>(
    val response: T? = null,
    val isLoading: Boolean = false,
    val exception: Throwable? = null
)