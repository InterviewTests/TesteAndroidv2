package com.zuptest.data.repository

import com.zuptest.data.remote.api.Api
import com.zuptest.domain.business.model.Account
import com.zuptest.domain.business.model.Credentials
import com.zuptest.domain.repository.LoginRepository
import io.reactivex.Observable

class LoginRepositoryImpl(val api: Api) : LoginRepository {
    override fun doLogin(credentials: Credentials): Observable<Account> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}