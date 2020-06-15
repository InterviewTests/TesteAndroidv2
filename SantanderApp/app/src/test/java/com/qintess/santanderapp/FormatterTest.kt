package com.qintess.santanderapp

import android.os.Build
import com.qintess.santanderapp.helper.Formatter
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.KITKAT])
class FormatterTest {
    // formatMoney deve retornar valor formatado
    @Test
    fun formatMoney_shouldReturnFormattedValue() {
        val positiveValue = 1234.56
        val negativeValue = -1234.56

        Assert.assertEquals("R$ 1.234,56", Formatter.formatMoney(positiveValue))
        Assert.assertEquals("R$ -1.234,56", Formatter.formatMoney(negativeValue))
    }

    // formatDate deve retornar data formata
    @Test
    fun formatDate_shouldReturnFormattedDate() {
        Assert.assertEquals("20/10/2020", Formatter.formatDate("2020-10-20"))
    }
}