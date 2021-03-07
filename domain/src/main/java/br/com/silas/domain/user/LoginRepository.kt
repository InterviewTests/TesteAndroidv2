package br.com.silas.domain.user

import br.com.silas.domain.ErrorResponse
import io.reactivex.rxjava3.core.Single

interface LoginRepository {
    fun fetchUser(login: String, password: String) : Single<Pair<User, ErrorResponse>>
}