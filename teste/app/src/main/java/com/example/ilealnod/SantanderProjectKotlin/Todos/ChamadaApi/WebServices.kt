package com.example.ilealnod.SantanderProjectKotlin.Todos.ChamadaApi

import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.AccountInfoResponse
import retrofit2.Call


class WebServices{

    // Função auxiliar para puxar os dados da api de statements pelo ID do usuario

     var service:GetCredentials = RetrofitCall().getCall().create(GetCredentials::class.java)

    fun getAccountInfo(userId:Int): Call<AccountInfoResponse> {
        val url = "statements/$userId"
        return service.getAccountInfo(url)
    }
}


