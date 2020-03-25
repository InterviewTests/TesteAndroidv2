package com.example.ibm_test.service

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class CacheInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (request.method() != "GET") {
            return chain.proceed(request)
        }

        val requestBuilder = request.newBuilder()

        requestBuilder.cacheControl(CacheControl.FORCE_NETWORK)

        try {
            val response = chain.proceed(requestBuilder.build())

            if (!response.isSuccessful) {
                val cachedResponse = lookupCache(chain, requestBuilder)

                if (cachedResponse != null) {
                    return cachedResponse
                }
            }

            return response
        } catch (ex: IOException) {
            return lookupCache(chain, requestBuilder) ?: throw ex
        }
    }

    private fun lookupCache(chain: Interceptor.Chain, requestBuilder: Request.Builder): Response? {
        val cachedResponse = chain.proceed(requestBuilder.cacheControl(CacheControl.FORCE_CACHE).build())

        return if(cachedResponse.isSuccessful) cachedResponse else null
    }

}