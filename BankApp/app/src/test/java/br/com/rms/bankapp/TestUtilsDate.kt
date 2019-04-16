package br.com.rms.bankapp

import br.com.rms.bankapp.utils.UtilsDate
import org.junit.Test
import kotlin.test.assertTrue

class TestUtilsDate {

    @Test
    fun validDate() {
        val date = "2019-12-01"
        val dateFormatted = UtilsDate.formatSimpleDate(date)
        assertTrue { dateFormatted == "01/12/2019" }
    }

    @Test
    fun emptyDate(){
        val date = ""
        val dateFormatted = UtilsDate.formatSimpleDate(date)
        assertTrue { dateFormatted == "" }
    }

    @Test
    fun invalidFormat(){
        val date = "2019/12/01"
        val dateFormatted = UtilsDate.formatSimpleDate(date)
        assertTrue { dateFormatted == "" }
    }

}