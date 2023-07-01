package com.nandoligeiro.safrando.domain.login.usecase.checkLogin

import com.nandoligeiro.safrando.domain.common.checkCPF.CheckCPFUseCase
import com.nandoligeiro.safrando.domain.common.checkEmail.CheckEmailUseCase
import com.nandoligeiro.safrando.domain.login.usecase.checkPassword.CheckPasswordUseCase
import javax.inject.Inject

class CheckLoginUseCaseImpl @Inject constructor(
    private val checkCPFUseCase: CheckCPFUseCase,
    private val checkEmailUseCase: CheckEmailUseCase,
    private val checkPasswordUseCase: CheckPasswordUseCase
) : CheckLoginUseCase {
    override fun invoke(user: String, password: String): Boolean {
        return (checkCPFUseCase(user) || checkEmailUseCase(user)) && checkPasswordUseCase(password)
    }
}