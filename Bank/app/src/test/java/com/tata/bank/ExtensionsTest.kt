package com.tata.bank

import com.tata.bank.utils.dateFormat
import com.tata.bank.utils.toAgencyFormat
import com.tata.bank.utils.toReais
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ExtensionsTest {

    @Test
    fun `When parse double to currency in reais, expect success`() {
        val value = 567.8
        val result = value.toReais()
        assertThat(result, equalTo("R$ 567,80"))
    }

    @Test
    fun `When parse big double to currency in reais, expect success`() {
        val value = 34167.0
        val result = value.toReais()
        assertThat(result, equalTo("R$ 34167,00"))
    }

    @Test
    fun `When parse string to agency expected format, expect success`() {
        val result = "123123123".toAgencyFormat()
        assertThat(result, equalTo("12.312312-3"))
    }

    @Test
    fun `When parse string to date expected format, expect success`() {
        val result = "2020-04-28".dateFormat()
        assertThat(result, equalTo("28/04/2020"))
    }

    @Test
    fun `When parse string from unexpected format, expect the incoming as result`() {
        val result = "2020/04/28".dateFormat()
        assertThat(result, equalTo("2020/04/28"))
    }
}