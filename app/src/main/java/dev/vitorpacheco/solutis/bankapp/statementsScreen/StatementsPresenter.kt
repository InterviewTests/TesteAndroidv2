package dev.vitorpacheco.solutis.bankapp.statementsScreen

import java.lang.ref.WeakReference

interface StatementsPresenterInput {
    fun presentStatementsData(response: StatementsResponse?)
}

class StatementsPresenter : StatementsPresenterInput {

    var output: WeakReference<StatementsActivityInput>? = null

    override fun presentStatementsData(response: StatementsResponse?) {
        response?.let {
            output?.get()?.displayStatementsData(StatementsViewModel(response.statementList, response.error?.message))
        }
    }

    companion object {
        var TAG = StatementsPresenter::class.java.simpleName
    }

}