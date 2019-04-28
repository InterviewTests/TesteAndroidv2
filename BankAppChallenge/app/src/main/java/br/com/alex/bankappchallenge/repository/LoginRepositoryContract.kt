package br.com.alex.bankappchallenge.repository

import br.com.alex.bankappchallenge.network.model.LoginRequest
import br.com.alex.bankappchallenge.network.model.LoginResponse
import br.com.alex.bankappchallenge.network.model.UserAccount
import io.reactivex.Single

interface LoginRepositoryContract {

    fun login(loginRequest: LoginRequest): Single<LoginResponse>
    fun saveUserLogin(user: String)
    fun saveUserAccount(userAccount: UserAccount)
    fun getUserLogin(): String
    fun getUserAccount(): UserAccount
    fun logout()
}