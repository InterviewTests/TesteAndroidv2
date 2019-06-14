package com.accenture.santander.remote.service.login

import com.accenture.santander.entity.Auth
import com.accenture.santander.remote.iservice.IUser
import com.accenture.santander.remote.connect.URL
import com.accenture.santander.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceLogin : IServiceLogin {

    override fun login(
        user: UserViewModel, success: (note: Response<Auth?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL.WEB_SERVICE)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IUser::class.java)

        val call = retrofit.login(user)
        call.enqueue(object : Callback<Auth?> {
            override fun onResponse(call: Call<Auth?>?, response: Response<Auth?>?) {
                response?.let {
                    success.invoke(it)
                }
            }

            override fun onFailure(call: Call<Auth?>?, t: Throwable) {
                failure.invoke(t)
            }
        })
    }
}
