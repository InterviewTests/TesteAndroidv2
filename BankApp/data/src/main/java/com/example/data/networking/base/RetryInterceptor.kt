package com.example.data.networking.base

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class RetryInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        if (!responseOkay(response.code)) {
            response.close()
            return retryCall(request, chain, 0)
        }
        return response
    }

    private fun retryCall(request: Request, chain: Interceptor.Chain, retryNumber: Int): Response {
        val newRequest = request.newBuilder().build()
        var another = chain.proceed(newRequest)
        if ((responseOkay(another.code)) && (retryNumber < 3)) {
            another.close()
            another = retryCall(newRequest, chain, retryNumber + 1)
        }
        return another
    }
}

fun responseOkay(code: Int): Boolean {
    return code >= 200 && code < 300
}