package br.com.vinicius.bankapp.data.repository

import br.com.vinicius.bankapp.data.remote.ApiService
import br.com.vinicius.bankapp.data.remote.model.LoginResponse
import br.com.vinicius.bankapp.domain.user.User
import br.com.vinicius.bankapp.domain.user.UserContract
import br.com.vinicius.bankapp.internal.BaseCallback
import br.com.vinicius.bankapp.internal.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository : Repository(), UserContract.IRepository {

    override fun startLogin(username: String, password: String, onResult:BaseCallback<User>) {

        ApiService.invoke()
            .login(username, password)
            .enqueue(object : Callback<LoginResponse>{
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    t.message?.let { onResult.onUnsuccessful(it) }
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if(response.body() == null || !response.isSuccessful) onResult.onUnsuccessful(response.message())

                    response.body()?.let {
                        if(it.error.code == 0)
                            onResult.onSuccessful(it.userAccount.toDomain(username, password))
                        else
                            onResult.onUnsuccessful(it.error.message)
                    }
                }

            })
    }
}