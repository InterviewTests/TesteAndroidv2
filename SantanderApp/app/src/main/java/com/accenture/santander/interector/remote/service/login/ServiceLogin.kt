package com.accenture.santander.interector.remote.service.login

import com.accenture.santander.viewmodel.User
import com.accenture.santander.entity.Auth
import com.accenture.santander.entity.Error
import com.accenture.santander.interector.remote.iservice.IUser
import com.accenture.santander.interector.remote.connect.URL
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import android.R.string
import org.json.JSONObject




class ServiceLogin : IServiceLogin {

    override fun login(
        user: User, success: (note: Response<Auth?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL.WEB_SERVICE)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IUser::class.java)

        val call = retrofit.login(user = user.login, password = user.password)
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
