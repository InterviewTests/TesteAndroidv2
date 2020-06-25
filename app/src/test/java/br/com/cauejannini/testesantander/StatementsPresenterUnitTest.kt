package br.com.cauejannini.testesantander

import android.os.Build
import br.com.cauejannini.testesantander.commons.Utils
import br.com.cauejannini.testesantander.login.*
import br.com.cauejannini.testesantander.statements.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.lang.ref.WeakReference

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class StatementsPresenterUnitTest {

    @Test
    fun present_correctData_onGetStatementsSuccessfull() {

        val presenter = StatementsPresenter()
        val outputSpy = StatementsActivityInputSpy()
        presenter.output = WeakReference(outputSpy)

        val responseModel = StatementsResponseModel()

        val list = ArrayList<Statement>()
        list.add(Statement("Crédito", "Transferência TED", "2020-05-30", 120.50))
        list.add(Statement("Débito", "Supermercado", "2020-05-29", 80.0))
        list.add(Statement("Débito", "Padaria", "2020-05-28", 30.90))

        responseModel.statementList = list
        responseModel.error = null

        presenter.presentStatementsResponse(responseModel)

        Assert.assertTrue(outputSpy.displayStatementsCalled)
        Assert.assertEquals(outputSpy.statementsListCopy, list)

        Assert.assertFalse(outputSpy.displayStatementsErrorCalled)
        Assert.assertNull(outputSpy.statementsErrorMessageCopy)

    }

    @Test
    fun present_correctData_onGetStatementsResponseError() {

        val presenter = StatementsPresenter()
        val outputSpy = StatementsActivityInputSpy()
        presenter.output = WeakReference(outputSpy)

        val responseModel = StatementsResponseModel()
        responseModel.statementList = null
        val errorMessage = "Erro devolvido pelo StatementsResponse"
        responseModel.error = errorMessage

        presenter.presentStatementsResponse(responseModel)

        Assert.assertTrue(outputSpy.displayStatementsErrorCalled)
        Assert.assertEquals(outputSpy.statementsErrorMessageCopy, errorMessage)

        Assert.assertFalse(outputSpy.displayStatementsCalled)
        Assert.assertNull(outputSpy.statementsListCopy)

    }

    @Test
    fun present_correctData_onGetStatementsError() {

        val presenter = StatementsPresenter()
        val outputSpy = StatementsActivityInputSpy()
        presenter.output = WeakReference(outputSpy)

        val errorMessage = "Erro de integração"

        presenter.presentStatementsFailed(errorMessage)

        Assert.assertTrue(outputSpy.displayStatementsErrorCalled)
        Assert.assertEquals(outputSpy.statementsErrorMessageCopy, errorMessage)

        Assert.assertFalse(outputSpy.displayStatementsCalled)
        Assert.assertNull(outputSpy.statementsListCopy)

    }

    @Test
    fun present_userData_withCorrectData() {

        val presenter = StatementsPresenter()
        val outputSpy = StatementsActivityInputSpy()
        presenter.output = WeakReference(outputSpy)

        val userAccount = UserAccount()
        userAccount.name = "João da Silva"
        userAccount.balance = 120.3
        userAccount.bankAccount = "1234"
        userAccount.agency = "567890"
        userAccount.userId = 1

        presenter.presentUserAccount(userAccount)

        Assert.assertTrue(outputSpy.displayUserDataCalled)
        Assert.assertEquals(outputSpy.userDataModelCopy.userName, userAccount.name)
        Assert.assertEquals(outputSpy.userDataModelCopy.saldo, Utils.currencyBrazil(userAccount.balance))

        val conta = userAccount.bankAccount
        val agencia = userAccount.agency
        Assert.assertEquals(outputSpy.userDataModelCopy.agenciaConta, "$conta / $agencia")

    }

    private class StatementsActivityInputSpy: StatementsActivityInput {

        var displayStatementsCalled = false
        var displayStatementsErrorCalled = false
        var displayUserDataCalled = false

        var statementsListCopy: List<Statement>? = null
        var statementsErrorMessageCopy: String? = null
        lateinit var userDataModelCopy: UserDataModel

        override fun displayStatements(statements: List<Statement>) {
            displayStatementsCalled = true
            statementsListCopy = statements
        }

        override fun displayStatementsError(message: String) {
            displayStatementsErrorCalled = true
            statementsErrorMessageCopy = message
        }

        override fun displayUserData(userDataModel: UserDataModel) {
            displayUserDataCalled = true
            userDataModelCopy = userDataModel
        }

    }

}