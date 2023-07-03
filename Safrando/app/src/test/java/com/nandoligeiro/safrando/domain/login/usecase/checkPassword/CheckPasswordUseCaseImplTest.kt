package com.nandoligeiro.safrando.domain.login.usecase.checkPassword

import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class  CheckPasswordUseCaseImplTest {

    private lateinit var checkPasswordUseCase: CheckPasswordUseCase

    @Before
    fun setup() {
        checkPasswordUseCase = CheckPasswordUseCaseImpl()
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `Check Password - SUCCESS`() {
        val result = checkPasswordUseCase.invoke("Safra@2023")
        assert(result)
    }

    @Test
    fun `Check Password - FAIL - NO UPPERCASE`() {
        val result = checkPasswordUseCase.invoke("safra@2023")
        Assert.assertFalse(result)
    }

    @Test
    fun `Check Password - FAIL - NO NUMBER`() {
        val result = checkPasswordUseCase.invoke("Safra@FDF")
        Assert.assertFalse(result)
    }

    @Test
    fun `Check Password - FAIL - NO SPECIAL CHARACTER`() {
        val result = checkPasswordUseCase.invoke("Safra2023")
        Assert.assertFalse(result)
    }
}
