package com.gustavo.bankandroid.features.login.usecase

import com.gustavo.bankandroid.common.contants.Constants
import com.gustavo.bankandroid.domain.contracts.LoginUseCases

class ValidateUsernameImpl : LoginUseCases.ValidateUserName {
    override fun invoke(username: String): Boolean {
        val matchCpf = username.matches(Constants.cpfRegex.toRegex())
        val matchEmail = username.matches(Constants.emailRegex.toRegex())
        return (matchCpf || matchEmail)
    }
}