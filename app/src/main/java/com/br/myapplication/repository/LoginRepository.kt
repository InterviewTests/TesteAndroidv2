package com.br.myapplication.repository

import com.br.myapplication.model.ResponseLogin
import com.br.myapplication.service.ApiResult
import com.br.myapplication.service.Requests
import java.lang.Exception

class LoginRepository(private val requests: Requests) {

    fun doLogin(user:String, password:String) : ApiResult<ResponseLogin> {
        return try {
            val result = requests.loginAsync(user, password).execute()
            if (result.isSuccessful) {
                result.body()?.let {
                    if (!it.error.code.isNullOrEmpty()) {
                        return ApiResult.Error(Throwable(it.error.message))
                    }
                    return ApiResult.Success(it)
                }
            }
            ApiResult.Error(Throwable("Erro genérico de API"))
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
}