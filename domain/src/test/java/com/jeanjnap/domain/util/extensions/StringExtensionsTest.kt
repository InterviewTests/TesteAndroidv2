package com.jeanjnap.domain.util.extensions

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.util.Date

class StringExtensionsTest {

    @Test
    fun formatStringAsDate() {
        assertEquals(Date(1610852400000), "2021-01-17".formatStringAsDate())
        assertNull("2021/01/17".formatStringAsDate())
    }

    @Test
    fun formatAgency() {
        assertEquals("01.111222-4", "011112224".formatAgency())
        assertEquals("01.111222-", "01111222".formatAgency())
    }
}