package com.nandoligeiro.safrando.domain.common.checkEmail

import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test

class CheckEmailUseCaseImplTest {

    private lateinit var checkEmailUseCase: CheckEmailUseCase

    @Before
    fun setup() {
        checkEmailUseCase = CheckEmailUseCaseImpl()

    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun checkEmail() {
        val result = checkEmailUseCase.invoke("nandoligeiro@gmail.com")
        assert(result)
    }

}
