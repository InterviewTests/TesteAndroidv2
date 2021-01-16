package com.jeanjnap.domain.entity.error

enum class ErrorRequestType(val code: Int) {
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    TOKEN_EXPIRED(409),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503);

    companion object {
        fun convert(code: Int?): ErrorRequestType {
            return enumValues<ErrorRequestType>().firstOrNull { it.code == code }
                ?: INTERNAL_SERVER_ERROR
        }
    }
}
