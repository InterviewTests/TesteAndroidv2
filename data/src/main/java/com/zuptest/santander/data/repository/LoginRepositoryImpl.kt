package com.zuptest.data.repository

import com.zuptest.data.remote.api.Api
import com.zuptest.data.remote.mapper.mapToEntity
import com.zuptest.data.remote.mapper.mapToRequest
import com.zuptest.santander.domain.business.model.Account
import com.zuptest.santander.domain.business.model.Credentials
import com.zuptest.santander.domain.repository.LoginRepository
import io.reactivex.Observable

class LoginRepositoryImpl(val api: Api) : LoginRepository {
    override fun doLogin(credentials: Credentials): Observable<Account> {
        return api.doLogin(credentials.mapToRequest()).map {
            it.accountResponse.mapToEntity()
        }
    }
}