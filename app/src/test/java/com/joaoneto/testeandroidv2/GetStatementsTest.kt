package com.joaoneto.testeandroidv2

import RetrofitInitializer
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class GetStatementsTest {

    @Test
    fun getStatementsSuccess() {

        val statementsService = RetrofitInitializer().statementsService()
        val call = statementsService.getStatements()

        try {
            val statementsResponse = call.execute()
            val body = statementsResponse.body()

            assertTrue(statementsResponse.isSuccessful && body?.statementList != null)

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}