package com.accenture.santander.ServiceTest

import com.accenture.santander.entity.ListStatement
import com.accenture.santander.interector.remote.connect.URL
import com.accenture.santander.interector.remote.iservice.IUser
import com.accenture.santander.interector.remote.service.statement.IServiceStatement
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Experimental(Experimental.Level.ERROR)
annotation class TestStatementApi

@TestStatementApi
class TestServiceStatement : IServiceStatement {

    @Test
    override fun statement(
        idUser: Int,
        success: (listStatement: Response<ListStatement?>) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL.WEB_SERVICE)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(IUser::class.java)

        val call = retrofit.statements(idUser.toString()).execute()

        if (call.isSuccessful) {
            success.invoke(call)
        } else {
            failure.invoke(Throwable(call.message()))
        }
    }
}