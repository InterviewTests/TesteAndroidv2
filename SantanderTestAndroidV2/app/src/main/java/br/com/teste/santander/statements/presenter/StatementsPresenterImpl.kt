package br.com.teste.santander.statements.presenter

import br.com.teste.santander.model.Statement
import br.com.teste.santander.statements.view.StatementsView
import java.lang.ref.WeakReference

class StatementsPresenterImpl: StatementsPresenter {

    lateinit var statementsView: WeakReference<StatementsView>

    override fun onRequestStatementsSuccess(itens: List<Statement>) {
        statementsView.get()?.onRequestStatementsSuccess(itens)
    }

    override fun onRequestStatementsError(error: String) {
        statementsView.get()?.onRequestStatementsError(error)
    }
}