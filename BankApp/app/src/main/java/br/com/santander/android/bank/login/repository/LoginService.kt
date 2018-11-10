package br.com.santander.android.bank.login.repository

import br.com.santander.android.bank.core.di.Injection
import br.com.santander.android.bank.login.domain.model.UserAccount
import br.com.santander.android.bank.login.domain.model.UserLogin
import io.reactivex.Observable

internal class LoginService : LoginApi {

    private val loginApi by lazy { Injection.retrofit.create(LoginApi::class.java) }

    override fun login(userLogin: UserLogin): Observable<UserAccount> {
        return loginApi.login(userLogin)
    }

}