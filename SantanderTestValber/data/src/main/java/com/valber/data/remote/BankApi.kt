package com.valber.data.remote

import com.valber.data.model.LoginResponse
import com.valber.data.model.StatementResponse
import com.valber.data.model.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BankApi {

    companion object {
        private const val LOGIN = "login"
        private const val STATEMENTS =  "statements/{id}"
    }
    @POST(LOGIN)
    fun logar(@Body user: User) : Single<LoginResponse>

    @GET(STATEMENTS)
    fun getStatement(@Path("id") id: Int) : Single<StatementResponse>
}