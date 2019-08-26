package com.valber.domain2

import com.valber.data.extensions.isAplhanumeric
import com.valber.data.extensions.isCharcacterSpecial
import com.valber.data.extensions.isLowCase
import com.valber.data.model.UserAccount
import com.valber.data.platform.NetworkHandler
import com.valber.data.repository.BankRepository
import com.valber.data.model.User
import io.reactivex.Single

class LoginCase(
    private val bankRepository: BankRepository,
    private val networkHandler: NetworkHandler
) {
    fun logar(user: String, password: String): Single<UserAccount> {
        return when (networkHandler.isConnected) {
            true -> sendLogin(user, password)
            false, null -> Single.error(Throwable("No internet"))
        }
    }

    private fun sendLogin(user: String, password: String): Single<UserAccount> {
        return when (password.isAplhanumeric() && password.isCharcacterSpecial() && password.isLowCase()) {
            true -> bankRepository.login(User(user, password))
            false -> Single.error(Throwable("Sua de senha deve conter  \n um caracter especial, \n um alfanumérico \n e uma letra minuscula"))
        }
    }

}



