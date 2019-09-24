package com.example.santandertestev2.domain.controller.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.santandertestev2.data.AccountService
import com.example.santandertestev2.data.PreferenceData
import com.example.santandertestev2.data.Rest
import com.example.santandertestev2.domain.model.controller.LoginResponse
import com.example.santandertestev2.domain.model.controller.UserAccount
import com.example.santandertestev2.domain.presenter.ILoginPresenter
import com.example.santandertestev2.domain.presenter.LoginPresenter
import com.example.santandertestev2.view.detail.DetailActivity
import com.example.santandertestev2.view.login.LoginActivity
import retrofit2.*

class LoginController(private val loginscreen: Context, private val loginPresenter : LoginPresenter) {

    private val preferenceData = PreferenceData(this.loginscreen)

    fun getLogin(login:String, password:String){
        val account = Rest.getRetrofitInstance()?.create(AccountService::class.java)
        val res = account?.login(login, password)
        res?.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginPresenter.showLoginError("Houve um erro ao efetuar o login. Tente mais tarde")
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                response.body()?.userAccount?.let {
                    preferenceData.saveLogged(it)
                    goDetail(it)
                }
            }
        })
    }

    fun autoLogin(){
        preferenceData.getUser()?.let {
            goDetail(it)
        }
    }

    private fun goDetail(user:UserAccount){
        val detailscreen = Intent(loginscreen.applicationContext, DetailActivity::class.java)
        val b = Bundle().apply {
            this.putSerializable("user", user)
        }
        detailscreen.putExtras(b)
        loginPresenter.goDetail(detailscreen)
    }
}