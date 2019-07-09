package br.com.learncleanarchitecture.login.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable


interface LoginEndpointsApi {
    @POST("login")
    fun login(@Body user: LoginApi.LoginRequestApi): Observable<LoginResult?>?
}