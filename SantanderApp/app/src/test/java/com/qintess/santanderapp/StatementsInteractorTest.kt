package com.qintess.santanderapp

import android.os.Build
import com.qintess.santanderapp.model.StatementModel
import com.qintess.santanderapp.model.UserModel
import com.qintess.santanderapp.service.*
import com.qintess.santanderapp.ui.view.statementsScreen.StatementsInteractor
import com.qintess.santanderapp.ui.view.statementsScreen.StatementsPresenterInput
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
class StatementsInteractorTest {
    private val userModel = UserModel(1, "Raphael", "000", "000", 100.0)

    @Mock
    lateinit var mockedStatementsService: StatementsService

    // Ao chamar fetchStatements com sucesso deve-se chamar presentStatements
    @Test
    fun onFetchStatementsSuccess_shouldCallPresentStatements() {
        `when`(mockedStatementsService.getHttpClient())
            .thenReturn(FakeSuccessHttpClient())
        val interactor = StatementsInteractor()
        interactor.statementsService = mockedStatementsService

        val statementsInteractorOutputSpy = StatementsInteractorOutputSpy()
        interactor.output = statementsInteractorOutputSpy

        interactor.fetchStatements(1)

        Assert.assertTrue(statementsInteractorOutputSpy.presentStatementsIsCalled)
    }

    // Ao chamar fetchStatements com erro deve-se chamar presentErrorMessage
    @Test
    fun onFetchStatementsFailure_shouldCallPresentErrorMessage() {
        `when`(mockedStatementsService.getHttpClient())
            .thenReturn(FakeFailureHttpClient())
        val interactor = StatementsInteractor()
        interactor.statementsService = mockedStatementsService

        val statementsInteractorOutputSpy = StatementsInteractorOutputSpy()
        interactor.output = statementsInteractorOutputSpy

        interactor.fetchStatements(1)

        Assert.assertTrue(statementsInteractorOutputSpy.presentErrorMessageIsCalled)
    }

    class StatementsInteractorOutputSpy: StatementsPresenterInput {
        var presentUserIsCalled = false
        var userCopy: UserModel? = null

        var presentStatementsIsCalled = false

        var presentErrorMessageIsCalled = false

        override fun presentStatements(statements: ArrayList<StatementModel>) {
            presentStatementsIsCalled = true
        }

        override fun presentErrorMessage(title: String, msg: String) {
            presentErrorMessageIsCalled = true
        }

    }

    open class FakeSuccessHttpClient: HttpInterface {
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

    open class FakeFailureHttpClient: HttpInterface {
        override fun post(url: String, bodyParameters: RequestParameters, onSuccess: SuccessCallback<JSONObject>, onFailure: FailureCallback) {
            onFailure(Exception("Nenhum método post para lançamentos"))
        }

        override fun get(url: String, urlParameters: RequestParameters, onSuccess: SuccessCallback<JSONObject>, onFailure: FailureCallback) {
            onFailure(java.lang.Exception("Erro ao buscar lançamentos"))
        }
    }
}