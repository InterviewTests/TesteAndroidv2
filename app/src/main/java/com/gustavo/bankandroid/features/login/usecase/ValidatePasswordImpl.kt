package com.gustavo.bankandroid.features.login.usecase

import com.gustavo.bankandroid.common.contants.Constants
import com.gustavo.bankandroid.domain.contracts.LoginUseCases

class ValidatePasswordImpl : LoginUseCases.ValidatePassword {
    override fun invoke(password: String): Boolean {
        return password.matches(Constants.strongPasswordRegex.toRegex())
    }
}