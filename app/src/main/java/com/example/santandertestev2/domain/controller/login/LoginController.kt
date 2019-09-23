package com.example.santandertestev2.domain.controller.login

import android.util.Log
import com.example.santandertestev2.data.AccountService
import com.example.santandertestev2.data.PreferenceData
import com.example.santandertestev2.data.Rest
import com.example.santandertestev2.domain.model.controller.LoginResponse
import com.example.santandertestev2.view.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginController(private val loginscreen: LoginActivity) {
    private val preferenceData = PreferenceData(this.loginscreen)

    fun getLogin(login:String, password:String){
        val account = Rest.getRetrofitInstance()?.create(AccountService::class.java)
        val res = account?.login(login, password)
        res?.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("ERRO", t.message)
                loginscreen.onLoginError()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.userAccount?.let {
                    preferenceData.saveLogged(it)
                    loginscreen.onLoginSuccessfull(it)
                }
            }
        })
    }

    fun autoLogin(){
        preferenceData.getUser()?.let {
            loginscreen.onLoginSuccessfull(it)
        }
    }

}