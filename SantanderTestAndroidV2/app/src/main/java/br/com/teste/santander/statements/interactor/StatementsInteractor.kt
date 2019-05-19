package br.com.teste.santander.statements.interactor

import android.content.Context

interface StatementsInteractor {
    fun getStatements(context: Context, userId: Int)
}