package br.com.teste.santander.spy

import br.com.teste.santander.model.Statement
import br.com.teste.santander.statements.view.StatementsView

class StatementsViewSpy: StatementsView {

    var onRequestStatementsSuccessCalled = false
    var onRequestStatementsError = false

    override fun init() {

    }

    override fun onRequestStatementsSuccess(itens: List<Statement>) {
        onRequestStatementsSuccessCalled = true
    }

    override fun onRequestStatementsError(error: String) {
        onRequestStatementsError = true
    }

}