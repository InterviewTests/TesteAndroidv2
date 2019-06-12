package br.com.douglas.fukuhara.bank.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class FormatterUtilsTest {

    @Test
    fun `Given A Plain Agency Number, Then Utils Should Format It With Mask`() {
        val plainAgencyNumber = "123456789"

        val formatAgencyWithMask = FormatterUtils.formatAgencyWithMask(plainAgencyNumber)

        assertEquals("12.345678-9", formatAgencyWithMask)
    }

    @Test
    fun `Given A Bank Account And Agency Number, Then Formatter Should Generate Formatted Banking Account Info`() {
        val agency = "1234"
        val plainAgencyNumber = "123456789"
        val formatAgencyWithMask = FormatterUtils.formatAgencyWithMask(plainAgencyNumber)

        val formatBankAccountAndAgency = FormatterUtils.formatBankAccountAndAgency(agency, formatAgencyWithMask)

        assertEquals("12.345678-9 / 1234", formatBankAccountAndAgency)
    }

    @Test
    fun `Given A Date In The Format YYYY-MM-DD, Then Format Should Convert To DDMMYYYY With Slash Separator`() {
        val formatDateToBrazilian = FormatterUtils.formatDateToBrazilian("2019-12-24")

        assertEquals("24/12/2019", formatDateToBrazilian)
    }

    @Test
    fun `Given A Positive BigDecimal Number, Then Formatter Must Include Currency`() {
        val originalBigDecimalValue = BigDecimal("3.45")

        val includeCurrencyInValue = FormatterUtils.includeCurrencyInValue(originalBigDecimalValue)

        assertEquals("R$3,45", includeCurrencyInValue)
    }

    @Test
    fun `Given A Negative BigDecimal Number, Then Formatter Must Include Currency With Negative Sign`() {
        val originalBigDecimalValue = BigDecimal("-3.45")

        val includeCurrencyInValue = FormatterUtils.includeCurrencyInValue(originalBigDecimalValue)

        assertEquals("- R$3,45", includeCurrencyInValue)
    }

    @Test
    fun `Given A Value With More Than Two Decimal Digits That Can Be Rounded Up, Then Formatter Should Round It Up`() {
        val originalBigDecimalValue = BigDecimal("3.456")

        val includeCurrencyInValue = FormatterUtils.includeCurrencyInValue(originalBigDecimalValue)

        assertEquals("R$3,46", includeCurrencyInValue)
    }

    @Test
    fun `Given A Negative Value With More Than Two Decimal Digits That Can Be Rounded Up, Then Formatter Should Round It Up`() {
        val originalBigDecimalValue = BigDecimal("-3.456")

        val includeCurrencyInValue = FormatterUtils.includeCurrencyInValue(originalBigDecimalValue)

        assertEquals("- R$3,46", includeCurrencyInValue)
    }

    @Test
    fun `Given A Value With More Than Two Decimal Digits That Shouldnt Be Rounded Up, Then Formatter Should Not Round It Up`() {
        val originalBigDecimalValue = BigDecimal("3.454")

        val includeCurrencyInValue = FormatterUtils.includeCurrencyInValue(originalBigDecimalValue)

        assertEquals("R$3,45", includeCurrencyInValue)
    }

    @Test
    fun `Given A Negative Value With More Than Two Decimal Digits That Shouldnt Be Rounded Up, Then Formatter Should Not Round It Up`() {
        val originalBigDecimalValue = BigDecimal("-3.454")

        val includeCurrencyInValue = FormatterUtils.includeCurrencyInValue(originalBigDecimalValue)

        assertEquals("- R$3,45", includeCurrencyInValue)
    }

    @Test
    fun `Given A String Message And Int Code, Then A Formatter Error Message Should Be Generated`() {
        val stringMessage = "Mensagem de erro"
        val intCode = 321
        val formatLoginErrorMsg = FormatterUtils.formatLoginErrorMsg(stringMessage, intCode)

        assertEquals("Mensagem de erro (321)", formatLoginErrorMsg)
    }
}