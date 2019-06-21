package com.santander.domain.repository

import com.santander.domain.entity.business.UserAccount
import io.reactivex.Completable
import io.reactivex.Observable

interface IAccountRepository {
    fun get(): Observable<UserAccount>
    fun save(account: UserAccount) : Completable
    fun clean() : Completable
}