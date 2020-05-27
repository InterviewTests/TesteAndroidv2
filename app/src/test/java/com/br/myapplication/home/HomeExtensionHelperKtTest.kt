package com.br.myapplication.home

import com.br.myapplication.helper.formatAgency
import com.br.myapplication.helper.formatDateString
import com.br.myapplication.helper.formatToMonetary
import org.junit.Test

import org.junit.Assert.*

class HomeExtensionHelperKtTest {

    @Test
    fun formatAgency() {
        val agency = "01111224"
        assertEquals(agency.formatAgency(), "01.11122-4")
    }

    @Test
    fun formatToMonetary() {
        val money = 1000.3332
        assertEquals(money.formatToMonetary(), "R$ 1000,33")
    }

    @Test
    fun formatDateString() {
        val date = "2018-07-25"
        assertEquals(date.formatDateString(), "25/07/2018")
    }
}