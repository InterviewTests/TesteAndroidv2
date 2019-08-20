package com.example.mybank

import com.example.mybank.utils.Helper
import org.junit.Test
import org.junit.Assert.*

class HelperTest {

    //------ Username

    @Test
    fun mustReturnFalse_whenReceiveInvalidCPF() {
        val cpf = "00022200022"
        val validation = Helper.isCPF(cpf)
        assertEquals(false, validation)
    }

    @Test
    fun mustReturnTrue_whenReceiveValidCPF() {
        val cpf = "08734723650"
        val validation = Helper.isCPF(cpf)
        assertEquals(true, validation)
    }

    //------ Password

    @Test
    fun mustReturnFalse_whenReceivePasswordWithLessThan6Char() {
        val password = "abc"
        val validation = Helper.validatePassword(password)
        assertEquals(false, validation)
    }


    @Test
    fun mustReturnFalse_whenReceivePasswordWithNoCApitalLetter() {
        val password = "abc123"
        val validation = Helper.validatePassword(password)
        assertEquals(false, validation)
    }

    @Test
    fun mustReturnFalse_whenReceivePasswordWithNoNumber() {
        val password = "abcdef"
        val validation = Helper.validatePassword(password)
        assertEquals(false, validation)
    }

    @Test
    fun mustReturnTrue_whenReceivePasswordWith_6OrMorChar_OneOrMoreCapitalLetter_OneOrMoreNumber() {
        val password = "Test@1"
        val validation = Helper.validatePassword(password)
        assertEquals(true, validation)
    }
}