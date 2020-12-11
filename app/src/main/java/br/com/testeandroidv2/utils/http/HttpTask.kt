package br.com.testeandroidv2.utils.http

import java.net.HttpURLConnection
import android.content.Context
import android.os.AsyncTask

import br.com.testeandroidv2.R

class HttpTask(private val context: Context,
               private val method: Int,
               private val headerParams: MutableList<HttpParams>?,
               private val requestParams: MutableList<HttpParams>?,
               private val jsonBody: String?,
               private val callBack: HttpCallBack?) : AsyncTask<String?, Void?, HttpResponse?>() {

    override fun doInBackground(vararg params: String?): HttpResponse? {
        val sendUrl: String? = params[0]
        val http = Http(requestParams, headerParams)
        return when (method) {
            HttpStatus.GET    -> http.sendGet(sendUrl)
            HttpStatus.POST   -> http.sendPost(sendUrl, jsonBody)
            HttpStatus.PUT    -> null
            HttpStatus.DELETE -> null
            else -> null
        }
    }

    override fun onPostExecute(response: HttpResponse?) {
        if (callBack != null && response != null) {
            val codeHttp = response.codeHttp

            if (codeHttp == HttpURLConnection.HTTP_OK ||
                codeHttp == HttpURLConnection.HTTP_CREATED) {
                if (response.status == "ERROR") {
                    callBack.onError(response)
                }
                else {
                    callBack.onResponse(response)
                }
            }
            else {
                if (codeHttp == HttpURLConnection.HTTP_CLIENT_TIMEOUT) {
                    response.message = context.getString(R.string.conection_error)
                }

                callBack.onError(response)
            }
        }
        else {
            callBack!!.onError(response)
        }
    }
}