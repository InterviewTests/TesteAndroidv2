package com.zuptest.domain.repository

import com.zuptest.domain.business.model.Account
import com.zuptest.domain.business.model.Credentials
import io.reactivex.Observable

interface LoginRepository {

    fun doLogin(credentials: Credentials): Observable<Account>
}