package br.com.rphmelo.bankapp.common.extensions

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DoubleExtTest {

    @Test
    fun should_assert_money_formating() {
        val value = 5123.99
        val formattedMoney = "R$ 5.123,99"
        Assert.assertEquals(value.formatMoney(), formattedMoney)
    }
}