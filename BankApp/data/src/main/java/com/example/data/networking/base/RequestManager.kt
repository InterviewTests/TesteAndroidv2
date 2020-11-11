package com.example.data.networking.base

import android.content.res.Resources
import com.example.data.networking.base.exceptions.ApiException
import com.example.data.networking.base.exceptions.RepositoryException
import com.example.data.networking.base.exceptions.SuccessWithNullReturnException
import retrofit2.Response
import java.util.concurrent.TimeoutException

object RequestManager {
    @Throws(RepositoryException::class)
    fun <T> getResponse(response: Response<T>): T {
        return getNullableResponse(response) ?: throw SuccessWithNullReturnException()
    }

    @Throws(RepositoryException::class)
    fun <T> getNullableResponse(response: Response<T>): T? {
        if (response.isSuccessful) {
            return response.body()
        } else {
            when (response.code()) {
                404 -> throw Resources.NotFoundException()
                504 -> throw TimeoutException()
            }
            val message = try {
                "There was an error while trying to process the request."
            } catch (e: Exception) {
                response.message()
            }
            throw ApiException(message)
        }
    }
}