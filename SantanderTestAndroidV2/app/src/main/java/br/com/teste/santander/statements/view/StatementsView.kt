package br.com.teste.santander.statements.view

import br.com.teste.santander.model.Statement

interface StatementsView {
    fun init()
    fun onRequestStatementsSuccess(itens: List<Statement>)
    fun onRequestStatementsError(error: String)
}