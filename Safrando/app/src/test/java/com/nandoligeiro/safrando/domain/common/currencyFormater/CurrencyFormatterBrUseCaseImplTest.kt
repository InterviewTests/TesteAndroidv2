package com.nandoligeiro.safrando.domain.common.currencyFormater

import com.nandoligeiro.safrando.domain.common.currencyFormatter.CurrencyFormatterUseCase
import com.nandoligeiro.safrando.domain.common.currencyFormatter.CurrencyFormatterBrUseCaseImpl
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CurrencyFormatterBrUseCaseImplTest {

    private lateinit var currencyFormatterBrUseCase: CurrencyFormatterUseCase

    @Before
    fun setup() {
        currencyFormatterBrUseCase = CurrencyFormatterBrUseCaseImpl()
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `CurrencyFormatterBrUseCase - SUCCESS`() {
        val oldValue = "\u00A0"
        val expected = "R$ 10.000,00"
        val result = currencyFormatterBrUseCase.invoke("10000")
        Assert.assertEquals(
            expected.replace(oldValue, " "),
            result.replace(oldValue, " ")
        )
    }

    @Test
    fun `CurrencyFormatterBrUseCase - FAIL`() {
        val oldValue = "\u00A0"
        val expected = "R$10.000,00"
        val result = currencyFormatterBrUseCase.invoke("10000")
        Assert.assertEquals(
            expected.replace(oldValue, " "),
            result.replace(oldValue, " ")
        )
    }
}
