package com.jeanjnap.domain.entity

import com.jeanjnap.domain.entity.error.RequestError

sealed class Response<T>
class EmptyResponse<T>(val error: RequestError) : Response<T>()
data class SuccessResponse<T>(val body: T) : Response<T>()
data class ErrorResponse<T>(val error: RequestError) : Response<T>()
