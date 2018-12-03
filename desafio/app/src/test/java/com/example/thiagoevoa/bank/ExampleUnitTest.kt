package com.example.thiagoevoa.bank

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun checkUserField(){
        com.example.thiagoevoa.bank.util.checkUserField("")
    }

    @Test
    fun checkPasswordField(){
        com.example.thiagoevoa.bank.util.checkPasswordField("")
    }

    @Test
    fun formateDate(){
        com.example.thiagoevoa.bank.util.formateDate("")
    }

    @Test
    fun isNumber(){
        com.example.thiagoevoa.bank.util.isNumber("")
    }
}
