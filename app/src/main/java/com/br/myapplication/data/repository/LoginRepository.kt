package com.br.myapplication.data.repository

import com.br.myapplication.data.model.ResponseLogin
import com.br.myapplication.data.service.Requests

class LoginRepository(private val requests: Requests) {

    suspend fun doLogin(user:String, password:String) : ResponseLogin = requests.loginAsync(user, password)

}