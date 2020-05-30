package com.br.myapplication.data.service

sealed class ApiResult<T> {
    data class Success<T>(val response: T) : ApiResult<T>()
    data class Error<T>(val throwable: Throwable): ApiResult<T>()
}