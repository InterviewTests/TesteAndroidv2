package com.nandoligeiro.safrando.domain.login.usecase.checkLogin

import com.nandoligeiro.safrando.domain.common.checkCPF.CheckCPFUseCase
import com.nandoligeiro.safrando.domain.common.checkCPF.CheckCPFUseCaseImpl
import com.nandoligeiro.safrando.domain.common.checkEmail.CheckEmailUseCase
import com.nandoligeiro.safrando.domain.common.checkEmail.CheckEmailUseCaseImpl
import com.nandoligeiro.safrando.domain.login.usecase.checkPassword.CheckPasswordUseCase
import com.nandoligeiro.safrando.domain.login.usecase.checkPassword.CheckPasswordUseCaseImpl
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CheckLoginUseCaseImplTest {

    private lateinit var checkLoginUseCase: CheckLoginUseCase
    private lateinit var checkCPFUseCase: CheckCPFUseCase
    private lateinit var checkEmailUseCase: CheckEmailUseCase
    private lateinit var checkPasswordUseCase: CheckPasswordUseCase

    @Before
    fun setup() {
        checkCPFUseCase = CheckCPFUseCaseImpl()
        checkEmailUseCase = CheckEmailUseCaseImpl()
        checkPasswordUseCase = CheckPasswordUseCaseImpl()
        checkLoginUseCase = CheckLoginUseCaseImpl(
            checkCPFUseCase,
            checkEmailUseCase,
            checkPasswordUseCase
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `Check FULL LOGIN - SUCCESS EMAIL`() {
        val user = "nandoligeiro@gmail.com"
        val password = "Safra@2023"
        val result = checkLoginUseCase.invoke(user, password)
        assert(result)
    }

    @Test
    fun `Check FULL LOGIN - SUCCESS CPF`() {
        val user = "919.491.310-25"
        val password = "Safra@2023"
        val result = checkLoginUseCase.invoke(user, password)
        assert(result)
    }

    @Test
    fun `Check FULL LOGIN - FAIL INVALID USER`() {
        val user = "nandoligeiro"
        val password = "Safra@2023"
        val result = checkLoginUseCase.invoke(user, password)
        Assert.assertFalse(result)
    }

    @Test
    fun `Check FULL LOGIN - FAIL INVALID PASSWORD`() {
        val user = "919.491.310-25"
        val password = "Safra2023"
        val result = checkLoginUseCase.invoke(user, password)
        Assert.assertFalse(result)
    }
}
