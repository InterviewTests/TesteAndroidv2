package br.com.santander.android.bank.login.repository

import br.com.santander.android.bank.login.domain.model.UserAccount
import br.com.santander.android.bank.login.domain.model.UserLogin
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

internal interface LoginApi {

    companion object { private const val LOGIN = "/api/login" }

    @POST(LOGIN)
    fun login(@Body userLogin: UserLogin): Observable<UserAccount>
}