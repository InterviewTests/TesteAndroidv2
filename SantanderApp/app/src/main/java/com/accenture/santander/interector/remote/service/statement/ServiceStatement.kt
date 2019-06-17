package com.accenture.santander.interector.remote.service.statement

import com.accenture.santander.entity.ListStatement
import com.accenture.santander.interector.remote.iservice.IUser
import com.accenture.santander.interector.remote.connect.URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceStatement : IServiceStatement {

    override fun statement(
        idUser: Int,
        success: (note: Response<ListStatement?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL.WEB_SERVICE)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IUser::class.java)

        val call = retrofit.statements(idUser.toString())
        call.enqueue(object : Callback<ListStatement?> {
            override fun onResponse(
                call: Call<ListStatement?>,
                response: Response<ListStatement?>
            ) {
                response.let {
                    success.invoke(it)
                }
            }

            override fun onFailure(call: Call<ListStatement?>, t: Throwable) {
                failure.invoke(t)
            }
        })
    }
}
