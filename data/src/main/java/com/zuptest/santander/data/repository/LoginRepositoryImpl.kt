package com.zuptest.santander.data.repository

import com.zuptest.santander.data.local.Preferences
import com.zuptest.santander.data.remote.api.Api
import com.zuptest.santander.data.remote.mapper.mapToEntity
import com.zuptest.santander.data.remote.mapper.mapToRequest
import com.zuptest.santander.domain.business.model.Account
import com.zuptest.santander.domain.business.model.Credentials
import com.zuptest.santander.domain.repository.LoginRepository
import io.reactivex.Completable
import io.reactivex.Observable


class LoginRepositoryImpl(
    private val api: Api,
    private val preferences: Preferences
) : LoginRepository {


    override fun doLogin(credentials: Credentials): Observable<Account> {
        return api.doLogin(credentials.mapToRequest()).map {
            it.accountResponse.mapToEntity()
        }
    }

    override fun saveLogin(login: String): Completable {
        preferences.saveEmail(login)
        return Completable.complete()
    }

    override fun getLastLogin(): Observable<String> {
        return Observable.just(preferences.retrieveEmail())
    }
}