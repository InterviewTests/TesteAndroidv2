package dev.vitorpacheco.solutis.bankapp.extensions

import org.junit.Assert.*
import org.junit.Test
import java.math.BigDecimal

class BigDecimalExtensionKtTest {

    @Test
    fun `test format with null object`() {
        val value: BigDecimal? = null

        assertNull(value.format())
    }

    @Test
    fun `test format with 0`() {
        assertEquals("R$ 0,00", BigDecimal.ZERO.format())
    }

    @Test
    fun `test format with values greather than 0`() {
        assertEquals("R$ 1.000,00", BigDecimal(1000).format())
        assertEquals("R$ 3,00", BigDecimal(3).format())
        assertEquals("R$ 34,50", BigDecimal(34.5).format())
    }

}