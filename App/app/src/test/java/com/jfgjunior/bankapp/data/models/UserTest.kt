package com.jfgjunior.bankapp.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class UserTest {

    @Test
    fun `verify if account and agency are formatted properly`() {
        val account = "2050"
        val agency = "012314564"

        val user = User(0, "", account, agency, 0f)

        val expected = "$account / $agency"
        val result = user.bankAccountAndAgency

        assertEquals(expected, result)
    }
}