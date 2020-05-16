package com.lucianogiardino.bankapp.data.repository

import com.lucianogiardino.bankapp.data.RetrofitClient
import com.lucianogiardino.bankapp.domain.model.LoginResponse
import com.lucianogiardino.bankapp.presentation.login.LoginContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository: LoginContract.Repository{
    override fun login(listener: LoginContract.UseCase.OnLoginUserResponse, username: String, password: String) {
        RetrofitClient.instance.login(username,password).enqueue(
            object: Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    listener.onLoginResponseFailed("Falha ao tentar efetuar o login")
                }
                override fun onResponse(call: Call<LoginResponse>,
                                        response: Response<LoginResponse>
                ) {

                    var message: String? = response.body()?.error?.message
                    var userAccount = response.body()!!.userAccount
                    if(message != null){
                        listener.onLoginResponseFailed(message)
                    }else{
                        listener.onLoginResponseSuccess(userAccount)
                    }
                }
            }
        )

    }

}