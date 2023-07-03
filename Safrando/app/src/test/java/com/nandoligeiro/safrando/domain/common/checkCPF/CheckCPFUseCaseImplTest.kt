package com.nandoligeiro.safrando.domain.common.checkCPF

import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CheckCPFUseCaseImplTest {

    private lateinit var checkCPFUseCase: CheckCPFUseCase

    @Before
    fun setup() {
        checkCPFUseCase = CheckCPFUseCaseImpl()
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
    fun `Check CPF - SUCCESS WITHOUT DOTS`() {
        val result = checkCPFUseCase.invoke("91949131025")
        assert(result)
    }

    @Test
    fun `Check CPF - ERROR`() {
        val result = checkCPFUseCase.invoke("919.491.310-252")
        Assert.assertFalse(result)
    }
}
