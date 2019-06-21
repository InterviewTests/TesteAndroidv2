package com.santander.data.repository

import com.santander.data.source.local.preferences.IPreferencesManager
import com.santander.domain.entity.business.UserAccount
import com.santander.domain.exception.NotFountAccountException
import com.santander.domain.repository.IAccountRepository
import io.reactivex.Completable
import io.reactivex.Observable

class AccountRepositoryImpl(private val pref: IPreferencesManager) : IAccountRepository {

    companion object {
        const val KEY_ACCOUNT = "key_account"
    }

    override fun get(): Observable<UserAccount> {
        return Observable.create {
            try {
                it.onNext(
                    pref.get(
                        key = KEY_ACCOUNT,
                        clazz = UserAccount::class.java
                    ).takeIf { pref.hasKey(KEY_ACCOUNT) }
                        ?: throw NotFountAccountException())
                it.onComplete()
            } catch (ex: Exception) {
                it.onError(ex)
            }
        }
    }

    // TODO sensitive information... review with IS(Information System)
    override fun save(account: UserAccount): Completable {
        return Completable.create {
            try {
                pref.save(key = KEY_ACCOUNT, value = account)
                it.onComplete()
            } catch (ex: Exception) {
                it.onError(ex)
            }
        }
    }

    override fun clean() : Completable {
        return Completable.create {
            try {
                pref.delete(key = KEY_ACCOUNT)
                it.onComplete()
            } catch (ex: Exception) {
                it.onError(ex)
            }
        }
    }
}