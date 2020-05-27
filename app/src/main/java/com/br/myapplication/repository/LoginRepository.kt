package com.br.myapplication.repository

import com.br.myapplication.model.ResponseLogin
import com.br.myapplication.service.ApiResult
import com.br.myapplication.service.Requests

class LoginRepository(private val requests: Requests) {

    suspend fun doLogin(user:String, password:String) : ResponseLogin = requests.loginAsync(user, password)

}