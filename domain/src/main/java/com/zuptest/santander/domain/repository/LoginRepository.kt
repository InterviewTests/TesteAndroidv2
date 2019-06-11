package com.zuptest.santander.domain.repository

import com.zuptest.santander.domain.business.model.Account
import com.zuptest.santander.domain.business.model.Credentials
import io.reactivex.Completable
import io.reactivex.Observable

interface LoginRepository {

    fun doLogin(credentials: Credentials): Observable<Account>
    fun saveLogin(login: String) : Completable
    fun getLastLogin(): Observable<String>

}