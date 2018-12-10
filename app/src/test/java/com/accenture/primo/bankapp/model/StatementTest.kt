package com.accenture.primo.bankapp.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class StatementTest {
    lateinit var objStatement: Statement

    @Before
    fun config() {
        objStatement = Statement("Titulo", "Descrição", "2018-11-28", 2189.98f)
    }

    @Test
    fun is_a_object_statement_valid() {

        Assert.assertEquals(Statement::class.java, objStatement::class.java)
        Assert.assertTrue(objStatement.title != "")
        Assert.assertTrue(objStatement.desc != "")

        try {
            var spf = SimpleDateFormat("yyyy-MM-dd")
            val newDate: Date = spf.parse(objStatement.date)
            Assert.assertTrue(newDate != null)
        }
        catch (ex: Exception){
            Assert.assertTrue("Data fora do formato!", false)
        }

        Assert.assertEquals(2189.98f, objStatement.value)
    }
}