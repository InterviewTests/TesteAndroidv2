package com.nandoligeiro.safrando.domain.common.checkCPF

import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NewCheckCPFUseCaseImplTest {

    private lateinit var checkCPFUseCase: CheckCPFUseCase

    @Before
    fun setup() {
        checkCPFUseCase = NewCheckCPFUseCaseImpl()
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `Check CPF - SUCCESS`() {
        val result = checkCPFUseCase.invoke("919.491.310-25")
        assert(result)
    }

    @Test
    fun `Check CPF - ERROR`() {
        val result = checkCPFUseCase.invoke("FSDFASDFASDFA")
        Assert.assertFalse(result)
    }
}
