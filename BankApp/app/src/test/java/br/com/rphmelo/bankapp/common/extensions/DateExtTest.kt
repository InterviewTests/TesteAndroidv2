package br.com.rphmelo.bankapp.common.extensions

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@RunWith(JUnit4::class)
class DateExtTest {

    @Test
    fun should_assert_date_formating() {
        val date = Date(18, 10, 30)
        val formattedDate = "30/11/1918"
        assertEquals(date.format(), formattedDate)
    }
}