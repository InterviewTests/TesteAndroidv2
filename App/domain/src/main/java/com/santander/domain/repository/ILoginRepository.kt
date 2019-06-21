package com.santander.domain.repository

import com.santander.domain.entity.business.UserAccount
import com.santander.domain.entity.input.SessionQuery
import io.reactivex.Completable
import io.reactivex.Observable

interface ILoginRepository {
    fun login(query: SessionQuery.SignIn): Observable<UserAccount>
    fun saveUser(user: String): Completable
    fun getUser(): Observable<String>
}