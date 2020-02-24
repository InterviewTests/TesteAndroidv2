package br.com.flaviokreis.santanderv2.data.network.util

import retrofit2.Response

class ApiResponse<T>(val response: Response<T>?) {
    companion object {
        fun <T> create(error: Throwable): ApiResponse<T> {
            return ApiResponse(null)
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return ApiResponse(response)
        }
    }
}