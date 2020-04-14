package com.example.testeandroidv2.data.repository.login

import com.example.testeandroidv2.data.ApiService
import com.example.testeandroidv2.domain.login.LoginBodyResponse
import com.example.testeandroidv2.domain.login.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginApiDataSource :
    LoginRepository {

    override fun login(user: User, loginResultCallback: (result: LoginResult) -> Unit) {
        ApiService.service.login(user).enqueue(object : Callback<LoginBodyResponse> {
            override fun onResponse(
                call: Call<LoginBodyResponse>,
                response: Response<LoginBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        response.body()?.let { loginResponse ->
                            loginResultCallback(
                                LoginResult.Success(
                                    loginResponse.userAccount
                                )
                            )
                        }
                    }
                    else -> loginResultCallback(
                        LoginResult.ApiError(
                            response.code()
                        )
                    )
                }
            }

            override fun onFailure(call: Call<LoginBodyResponse>, t: Throwable) {
                loginResultCallback(LoginResult.ServerError)
            }
        })
    }
}