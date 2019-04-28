package br.com.alex.bankappchallenge.interactor

import br.com.alex.bankappchallenge.model.Login
import br.com.alex.bankappchallenge.network.model.UserAccount

interface LoginInteractorContract {
    fun login(login: Login)
    fun getUserAccount(): UserAccount
    fun loginInteractorOutputImpl(loginInteractorOutput: LoginInteractorOutput)
    fun clear()
    fun hasUser()
}