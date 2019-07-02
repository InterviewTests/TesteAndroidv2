package com.br.rafael.TesteAndroidv2

import com.br.rafael.TesteAndroidv2.Util.Utils
import org.junit.Assert
import org.junit.Test

class UtilsTest {

    @Test
    fun sucess_convertMoney(){

        val money = 745.03
        val valueFormat = "R$ 745,03"

        val result = Utils.converMoney(money.toFloat())

        Assert.assertEquals(result,valueFormat)
    }

    @Test
    fun sucess_formatData() {
        val dataInput = "2018-06-23"
        val dataOutPut = "23/06/2018"

        val result = Utils.stringData(dataInput)

        Assert.assertEquals(dataOutPut,result)
    }
}