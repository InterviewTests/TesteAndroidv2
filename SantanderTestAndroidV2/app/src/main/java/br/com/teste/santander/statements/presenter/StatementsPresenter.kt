package br.com.teste.santander.statements.presenter

import br.com.teste.santander.model.Statement

interface StatementsPresenter {
    fun onRequestStatementsSuccess(itens: List<Statement>)
    fun onRequestStatementsError(error: String)
}