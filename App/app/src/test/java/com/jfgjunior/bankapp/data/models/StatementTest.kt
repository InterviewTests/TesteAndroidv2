package com.jfgjunior.bankapp.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class StatementTest {

    @Test
    fun `verify if day, month and year are separated by slashes`() {
        val statement = Statement("", "", "2020-12-12", 0f)

        val expected = "12/12/2020"
        val result = statement.formattedDate

        assertEquals(expected, result)
    }

    @Test
    fun `verify if statement returns the raw result in case of an error`() {
        val statement = Statement("", "", "20201212", 0f)

        val expected = "20201212"
        val result = statement.formattedDate

        assertEquals(expected, result)
    }
}