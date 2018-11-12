package br.com.ibm.teste.android.services.api.statement

import br.com.ibm.teste.android.services.models.StatementsResponse

/**
 * Created by paulo.
 * Date: 12/11/18
 * Time: 11:43
 */
interface IStatementListener {
    fun showLoading()
    fun hideLoading()
    fun responseError(messageError: String)
    fun responseSuccess(statementResponse: StatementsResponse)
}