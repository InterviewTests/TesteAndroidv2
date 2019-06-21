package com.santander.data.repository

import com.santander.data.mapper.AccountMapper
import com.santander.data.source.local.preferences.IPreferencesManager
import com.santander.data.source.remote.API
import com.santander.domain.entity.business.UserAccount
import com.santander.domain.entity.input.SessionQuery
import com.santander.domain.repository.ILoginRepository
import io.reactivex.Completable
import io.reactivex.Observable

class LoginRepositoryImpl(private val api: API, private val pref: IPreferencesManager) : ILoginRepository {

    companion object {
        const val KEY_USER = "key_user"
    }

    override fun login(query: SessionQuery.SignIn): Observable<UserAccount> {
        return api.login(user = query.user, password = query.password)
            .map { AccountMapper().toEntity(from = it.userAccount) }
    }

    override fun saveUser(user: String): Completable {
        return Completable.create {
            try {
                pref.save(KEY_USER, user)
                it.onComplete()
            } catch (ex: Exception) {
                it.onError(ex)
            }
        }
    }

    override fun getUser(): Observable<String> {
        return Observable.create {
            try {
                it.onNext(pref.get(KEY_USER, String::class.java).takeIf { pref.hasKey(KEY_USER) }
                    ?: throw Exception("User not fount"))
                it.onComplete()
            } catch (ex: Exception) {
                it.onError(ex)
            }
        }
    }

}