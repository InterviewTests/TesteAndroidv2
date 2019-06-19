package br.com.vinicius.bankapp.data.repository

import br.com.vinicius.bankapp.data.remote.model.LoginModel
import br.com.vinicius.bankapp.infra.BaseCallback
import br.com.vinicius.bankapp.infra.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository : Repository( ) {

    fun login(username: String, password: String, onResult:BaseCallback<LoginModel>) {
        super.data.restApi()
            .login(username, password)
            .enqueue(object : Callback<LoginModel>{
                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    t.message?.let { onResult.onUnsuccessful(it) }
                }

                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    if(response.body() != null)
                        response.body()?.let { onResult.onSuccessful(it) }
                }

            })
    }
}