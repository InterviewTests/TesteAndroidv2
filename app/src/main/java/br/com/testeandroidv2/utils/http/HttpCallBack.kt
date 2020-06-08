package br.com.testeandroidv2.utils.http

interface HttpCallBack {
    fun onResponse(response: HttpResponse?)
    fun onError(response: HttpResponse?)
}