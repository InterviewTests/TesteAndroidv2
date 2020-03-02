package com.lucianogiardino.bankapp.domain.usecase

import com.lucianogiardino.bankapp.data.RetrofitClient
import com.lucianogiardino.bankapp.ui.login.LoginContract
import com.lucianogiardino.bankapp.domain.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginUseCase: LoginContract.UseCase.LoginUser {

    override fun execute(listener: LoginContract.Presenter.OnLoginResponse, username: String, password: String){

        RetrofitClient.instance.login(username,password).enqueue(
            object: Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    listener.onLoginResponseFailed("Falha ao tentar efetuar o login")
                }
                override fun onResponse(call: Call<LoginResponse>,
                                        response: Response<LoginResponse>) {

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