package com.example.data.networking.base.exceptions

class SuccessWithNullReturnException(
    message: String? = null,
    cause: Throwable? = null
) : ApiException(message, cause)