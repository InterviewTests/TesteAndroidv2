package com.br.myapplication.service

sealed class ApiResult<T> {

    data class Success<T>(val response: T) : ApiResult<T>()
    data class Error<T>(val throwable: Throwable): ApiResult<T>()
}