package com.jeanjnap.data.api

import com.jeanjnap.data.mapper.Mapper
import com.jeanjnap.data.model.error.ApiError
import com.jeanjnap.data.util.moshi.InternalMoshiImpl
import com.jeanjnap.domain.entity.EmptyResponse
import com.jeanjnap.domain.entity.ErrorResponse
import com.jeanjnap.domain.entity.SuccessResponse
import com.jeanjnap.domain.entity.error.ErrorRequestType
import com.jeanjnap.domain.entity.error.RequestError
import com.jeanjnap.domain.entity.success.SuccessRequestType
import retrofit2.Response
import java.io.IOException

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                val emptyBody =
                    (body is List<*> && body.size == 0) || response.code() == SuccessRequestType.CREATED.code
                if (body == null || emptyBody) {
                    ApiEmptyResponse(
                        ApiError(response.code(), response.message(), response.message())
                    )
                } else {
                    ApiSuccessResponse(body = body)
                }
            } else {
                val messageClassError = parserStringJsonToApiError(response.errorBody()?.string())
                val msg = response.errorBody()?.string()
                val errorData =
                    if (messageClassError?.status != null) messageClassError else ApiError(
                        response.code(),
                        messageClassError?.error,
                        msg
                    )
                ApiErrorResponse(errorData)
            }
        }
    }
}

class ApiEmptyResponse<T>(val error: ApiError) : ApiResponse<T>()
data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()
data class ApiErrorResponse<T>(val error: ApiError) : ApiResponse<T>()

private fun <T, O> ApiResponse<T>.converter(mapper: Mapper<T, O>): com.jeanjnap.domain.entity.Response<O> {
    return when (this) {
        is ApiEmptyResponse -> EmptyResponse(
            RequestError(
                ErrorRequestType.convert(error.status),
                error.errorMessage ?: error.error
            )
        )
        is ApiSuccessResponse -> {
            val itemConverted = mapper.transform(body)
            SuccessResponse(itemConverted)
        }
        is ApiErrorResponse -> ErrorResponse(
            RequestError(
                ErrorRequestType.convert(error.status),
                error.errorMessage ?: error.error
            )
        )
    }
}

fun <T, O> Response<T>.create(mapper: Mapper<T, O>): com.jeanjnap.domain.entity.Response<O> {
    return ApiResponse.create(this).converter(mapper)
}

fun parserStringJsonToApiError(bodyError: String?): ApiError? {
    val moshi = InternalMoshiImpl()
    val adapter = moshi.getMoshi().adapter(ApiError::class.java)
    return if (bodyError == null) {
        null
    } else try {
        adapter.fromJson(bodyError)
    } catch (e: IOException) {
        null
    }
}
