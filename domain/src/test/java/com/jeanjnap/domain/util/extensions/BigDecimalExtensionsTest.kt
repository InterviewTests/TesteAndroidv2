package com.jeanjnap.domain.util.extensions

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class BigDecimalExtensionsTest {

    @Test
    fun formatCurrency() {
        assertEquals("R$ 10,00", BigDecimal.TEN.formatCurrency())
        assertEquals("R$ 1.000,00", "1000".toBigDecimal().formatCurrency())
        assertEquals("R$ 0,99", "0.99".toBigDecimal().formatCurrency())
    }
}