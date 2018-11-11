package br.com.santander.android.bank.extensions

import br.com.santander.android.bank.core.extensions.toCurrency
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ConversionTest {

    @Test
    fun `Assert float conversion to currency format`() {
        val expected = "R$ 1.000,56"
        val value = 1000.56f
        assertEquals(value.toCurrency(), expected)
    }
}