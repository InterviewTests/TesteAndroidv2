package com.jeanjnap.domain.entity.error

data class RequestError(
    val status: ErrorRequestType? = null,
    val errorMessage: String? = null
)
