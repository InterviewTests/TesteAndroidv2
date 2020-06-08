package br.com.testeandroidv2.utils.http

import java.net.HttpURLConnection
import android.content.Context
import org.json.JSONObject

import br.com.testeandroidv2.utils.Utils

class HttpAction(private val context: Context) {
    private val requestParams:  MutableList<HttpParams> = emptyList<HttpParams>().toMutableList()
    private val headerParams: MutableList<HttpParams> = emptyList<HttpParams>().toMutableList()

    fun add(name: String?, value: String?)       { requestParams.add(HttpParams(name, value))  }
    fun addHeader(name: String?, value: String?) { headerParams.add(HttpParams(name, value)) }

    fun send(method: Int, urlSend: String, jsonBody: String?, callBack: HttpCallBack) {
        if (!Utils.isInternetConnect(context)) {
            callBack.onResponse(errorResponse())
            return
        }

        HttpTask(
            context,
            method,
            headerParams,
            requestParams,
            jsonBody,
            object : HttpCallBack {
                override fun onResponse(response: HttpResponse?) { callBack.onResponse(response) }
                override fun onError(response: HttpResponse?)    { callBack.onError(response) }
            }
        ).execute(urlSend)
    }

    private fun errorResponse(): HttpResponse {
        val response = HttpResponse()
            response.codeHttp = HttpURLConnection.HTTP_BAD_REQUEST
            response.codeError = 99
            response.message = "Internet Error"
            response.status = "Error"
        return response
    }
}