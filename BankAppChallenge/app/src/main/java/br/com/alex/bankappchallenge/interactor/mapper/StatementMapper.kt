package br.com.alex.bankappchallenge.interactor.mapper

import br.com.alex.bankappchallenge.extensions.asBRL
import br.com.alex.bankappchallenge.extensions.formatDate
import br.com.alex.bankappchallenge.model.FormatedStatement
import br.com.alex.bankappchallenge.network.model.Statement

interface StatementMapperContract {
    fun mapper(statementList: List<Statement>): List<FormatedStatement>
}

class StatementMapper : StatementMapperContract {
    override fun mapper(statementList: List<Statement>) =
        statementList.map {
            FormatedStatement(
                it.title,
                it.desc,
                it.date.formatDate(),
                it.value.asBRL()
            )
        }
}