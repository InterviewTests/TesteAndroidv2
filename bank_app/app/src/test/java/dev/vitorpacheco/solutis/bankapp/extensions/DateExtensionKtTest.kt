package dev.vitorpacheco.solutis.bankapp.extensions

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class DateExtensionKtTest {

    @Test
    fun `test format with null object`() {
        val value: Date? = null

        assertNull(value.format())
    }

    @Test
    fun `test format with valid values`() {
        val calendar = Calendar.getInstance()

        calendar.set(2000, 2, 3)
        assertEquals("03/03/2000", calendar.time.format())

        calendar.set(1989, 5, 12)
        assertEquals("12/06/1989", calendar.time.format())

        calendar.set(2019, 11, 4)
        assertEquals("04/12/2019", calendar.time.format())
    }

}