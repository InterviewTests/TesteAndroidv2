package com.accenture.primo.bankapp

import org.junit.Test
import org.junit.Assert.*
import com.accenture.primo.bankapp.extension.*

class ExtensionsTest {
    val text = "Aqui tem 1 numero, @ caracter especial e Letra Maiuscula."
    val emailtext = "jose@bankapp.com"
    val CPF = "294.329.016-53"
    val password = "1qA@"
    val datetest = "2018-11-28"
    val moneyToBR = 1024.32f

    @Test
    fun format_currency_to_BR() {
        assertTrue(moneyToBR.toMoney().contains("R$"))
    }

    @Test
    fun text_has_one_number() {
        assertTrue(text.hasOneNumber())
    }

    @Test
    fun text_has_one_special_caracter() {
        assertTrue(text.hasOneSpecialCaracter())
    }

    @Test
    fun text_has_one_uppercase() {
        assertTrue(text.hasOneUpperCase())
    }

    @Test
    fun is_a_email_valid() {
        assertTrue(emailtext.isEmail())
    }

    @Test
    fun is_a_valid_CPF() {
        assertTrue(CPF.isCPF())
    }

    @Test
    fun is_a_valid_password() {
        val check = (password.hasOneNumber() || password.hasOneSpecialCaracter() || password.hasOneUpperCase())
        assert(check)
    }

    @Test
    fun is_a_valid_formated_date() {
        assertEquals("28/11/2018", datetest.formatDate())
    }
}