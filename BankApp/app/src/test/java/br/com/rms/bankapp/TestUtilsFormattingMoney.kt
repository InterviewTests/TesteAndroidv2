package br.com.rms.bankapp

import br.com.rms.bankapp.utils.UtilsMoneyFormatting
import org.junit.Test
import kotlin.test.assertTrue

class TestUtilsFormattingMoney {

    @Test
    fun checksTheResultOfFormatting() {
        val testValue = 12.00
        assertTrue { UtilsMoneyFormatting.simpleMoneyFormmat(testValue) == "R$ 12,00" }
    }

}