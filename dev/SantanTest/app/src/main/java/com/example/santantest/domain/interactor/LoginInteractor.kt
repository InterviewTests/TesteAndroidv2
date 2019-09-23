package com.example.santantest.domain.interactor

import android.util.Log
import com.example.santantest.data.AccountService
import com.example.santantest.data.Network
import com.example.santantest.domain.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginInteractor(){

    fun doLogin(login:String, password: String, callback: LoginInteractorListener){

        val accountService = Network.getInstance()?.create(AccountService::class.java)
        val response = accountService?.login(login, password)
        response?.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("ERROR", t.message)
                callback.onLoginError()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.userAccount?.let {
                    callback.onLoginSuccess(it)
                }
            }

        })

    }


}