package br.com.cauejannini.testesantander.statements

import br.com.cauejannini.testesantander.commons.Utils
import br.com.cauejannini.testesantander.login.UserAccount
import java.lang.ref.WeakReference

interface StatementsPresenterInput {
    fun presentStatementsResponse(statementsResponse: StatementsResponseModel)
    fun presentStatementsFailed(message: String)
    fun presentUserAccount (userAccount: UserAccount?)
}

class StatementsPresenter: StatementsPresenterInput {

    var output: WeakReference<StatementsActivityInput>? = null

    override fun presentStatementsResponse(statementsResponse: StatementsResponseModel) {
        val statementList = statementsResponse.statementList
        if (statementList != null) {
            output?.get()?.displayStatements(statementList)
        } else {
            output?.get()?.displayStatementsError(statementsResponse.error.toString())
        }
    }

    override fun presentStatementsFailed(message: String) {
        output?.get()?.displayStatementsError(message)
    }

    override fun presentUserAccount(userAccount: UserAccount?) {

        val userDataModel = UserDataModel()

        if (userAccount != null) {

            userDataModel.userName = if (userAccount.name != null) userAccount.name else "-"
            val agencia = if (userAccount.agency != null) userAccount.agency else "-"
            val conta = if (userAccount.bankAccount != null) userAccount.bankAccount else "-"
            userDataModel.agenciaConta = "$conta / $agencia"

            val saldo = if (userAccount.balance != null) Utils.currencyBrazil(userAccount.balance) else "-"
            userDataModel.saldo = saldo

        } else {
            userDataModel.userName = "-"
            userDataModel.agenciaConta = "- / -"
            userDataModel.saldo = "-"
        }

        output?.get()?.displayUserData(userDataModel)
    }

}