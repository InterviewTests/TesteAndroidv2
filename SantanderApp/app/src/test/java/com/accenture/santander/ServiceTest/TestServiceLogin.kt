package com.accenture.santander.ServiceTest

import com.accenture.santander.entity.Auth
import com.accenture.santander.interector.remote.connect.URL
import com.accenture.santander.interector.remote.iservice.IUser
import com.accenture.santander.interector.remote.service.login.IServiceLogin
import com.accenture.santander.viewmodel.User
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Experimental(Experimental.Level.ERROR)
annotation class TestLoginApi

@TestLoginApi
class TestServiceLogin : IServiceLogin {

    @Test
    override fun login(
        user: User,
        success: (note: Response<Auth?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL.WEB_SERVICE)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IUser::class.java)

        val call = retrofit.login(user = user.login, password = user.password).execute()

        if (call.isSuccessful) {
            success.invoke(call)
        } else {
            failure.invoke(Throwable(call.message()))
        }
    }
}