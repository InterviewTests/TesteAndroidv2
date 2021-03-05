package br.com.silas.domain.user

import io.reactivex.rxjava3.core.Single

interface LoginRepository {
    fun fetchUser(login: String, password: String) : Single<User>
}