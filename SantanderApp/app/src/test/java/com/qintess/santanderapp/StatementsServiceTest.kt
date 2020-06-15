package com.qintess.santanderapp

import android.os.Build
import com.qintess.santanderapp.model.StatementModel
import com.qintess.santanderapp.service.*
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner
import org.robolectric.annotation.Config

@RunWith(MockitoJUnitRunner::class)
@Config(sdk = [Build.VERSION_CODES.KITKAT])
class StatementsServiceTest {
    @Mock
    private lateinit var statementsService: StatementsService

    // Um dos callbacks é chamado
    @Test
    fun getStatementsCallback_isCalled() {
        // Esse teste só funciona pq o FakeHttpClient sobrescreve a funcionalidade da requisição e executa o código sincronamente. No uso real esse código é executado assincronamente
        var callbackIsCalled = false
        val successCallback: SuccessCallback<ArrayList<StatementModel>> = {
            callbackIsCalled = true
        }
        val failureCallback: FailureCallback = {
            callbackIsCalled = true
        }
        `when`(statementsService.getHttpClient())
            .thenReturn(FakeHttpClient())

        statementsService.getStatements(1, successCallback, failureCallback)

        Assert.assertTrue(callbackIsCalled)
    }

    // getStatements deve retornar lista de StatementModel
    @Test
    fun getStatements_shouldReturnNotNullStatementModelArrayList() {
        val successCallback: SuccessCallback<ArrayList<StatementModel>> = {
            Assert.assertNotNull(it)
        }
        val failureCallback: FailureCallback = {
            throw it
        }
        `when`(statementsService.getHttpClient())
            .thenReturn(FakeHttpClient())

        statementsService.getStatements(1, successCallback, failureCallback)
    }

    // getStatements deve retornar lista com número correto de items
    @Test
    fun getStatements_shouldReturnCorretSizeStatementModelArrayList() {
        val successCallback: SuccessCallback<ArrayList<StatementModel>> = {
            Assert.assertEquals(2, it.size)
        }
        val failureCallback: FailureCallback = {
            throw it
        }
        `when`(statementsService.getHttpClient())
            .thenReturn(FakeHttpClient())

        statementsService.getStatements(1, successCallback, failureCallback)
    }

    open class FakeHttpClient: HttpInterface {
        override fun post(url: String, bodyParameters: RequestParameters, onSuccess: SuccessCallback<JSONObject>, onFailure: FailureCallback) {
            onFailure(Exception("Nenhum método post para lançamentos"))
        }

        override fun get(url: String, urlParameters: RequestParameters, onSuccess: SuccessCallback<JSONObject>, onFailure: FailureCallback) {
            onSuccess(
                JSONObject("""
                    {
                        "statementList": [
                            {
                                "title": "Pagamento",
                                "desc": "Conta de luz",
                                "date": "2018-08-15",
                                "value": -50
                            },
                            {
                                "title": "TED Recebida",
                                "desc": "Joao Alfredo",
                                "date": "2018-07-25",
                                "value": 745.03
                            }
                        ]
                    }
                """)
            )
        }
    }
}