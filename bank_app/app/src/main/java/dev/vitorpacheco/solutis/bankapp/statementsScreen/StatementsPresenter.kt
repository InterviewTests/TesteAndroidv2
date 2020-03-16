package dev.vitorpacheco.solutis.bankapp.statementsScreen

import dev.vitorpacheco.solutis.bankapp.extensions.format
import java.lang.ref.WeakReference

interface StatementsPresenterInput {
    fun presentStatementsData(response: StatementsResponse?)
}

class StatementsPresenter : StatementsPresenterInput {

    var output: WeakReference<StatementsActivityInput>? = null

    override fun presentStatementsData(response: StatementsResponse?) {
        response?.let {
            val statements = response.statementList?.map {
                StatementViewModel(
                    title = it.title,
                    desc = it.desc,
                    date = it.date.format(),
                    value = it.value?.format()
                )
            }

            output?.get()
                ?.displayStatementsData(StatementsViewModel(statements, response.error?.message))
        }
    }

    companion object {
        var TAG = StatementsPresenter::class.java.simpleName
    }

}

