package com.nandoligeiro.safrando.domain.login.usecase.checkPassword

import javax.inject.Inject

class CheckPasswordUseCaseImpl @Inject constructor(): CheckPasswordUseCase {
    override fun invoke(password: String): Boolean {
        if (password.firstOrNull { it.isDigit() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false
        if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false

        return true
    }
}
