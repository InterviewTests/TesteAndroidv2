package com.jfgjunior.bankapp.data.models

data class ResponseError(
    val code: Int,
    val message: String
) {
    companion object {
        @JvmStatic
        val genericError = ResponseError(code = -1, message = "")
    }
}