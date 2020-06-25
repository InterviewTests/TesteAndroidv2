package br.com.cauejannini.testesantander.commons.integracao

import android.content.Context

class ApiRepository() {

    fun get(): Api {
        return RetrofitService().createService(Api::class.java)
    }

}