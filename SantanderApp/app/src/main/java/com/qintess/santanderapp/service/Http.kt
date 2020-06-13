package com.qintess.santanderapp.service

import android.util.Log
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

typealias RequestParameters = HashMap<String, Any?>
typealias SuccessCallback<T> = (T) -> Unit
typealias FailureCallback = (Exception) -> Unit

interface ServiceInterface {
    fun getHttpClient(): HttpInterface
}

interface HttpInterface {
    fun post(url: String, bodyParameters: RequestParameters, onSuccess: SuccessCallback<JSONObject>, onFailure: FailureCallback)
    fun get(url: String, urlParameters: RequestParameters, onSuccess: SuccessCallback<JSONObject>, onFailure: FailureCallback)
}

class Http: HttpInterface {
    private val TAG = this::class.java.name
    private val API_URL = "https://bank-app-test.herokuapp.com/api/"
    private val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
    private val client = OkHttpClient()

    override fun post(url: String, bodyParameters: RequestParameters, onSuccess: SuccessCallback<JSONObject>, onFailure: FailureCallback) {
        val stringifiedBody = JSONObject(bodyParameters).toString()
        val body = stringifiedBody.toRequestBody(JSON)
        val request = Request.Builder()
            .url(API_URL + url)
            .post(body)
            .build()

        Thread {
            val response = client.newCall(request).execute()

            response.body?.let {
                onSuccess(JSONObject(it.string()))
            }

            val errosMsg = "Resposta do servidor veio vazia"
            Log.e(TAG, errosMsg)
            onFailure(Exception(errosMsg))
        }.start()
    }

    override fun get(
        url: String,
        urlParameters: RequestParameters,
        onSuccess: SuccessCallback<JSONObject>,
        onFailure: FailureCallback
    ) {
        TODO("Not yet implemented")
    }
}