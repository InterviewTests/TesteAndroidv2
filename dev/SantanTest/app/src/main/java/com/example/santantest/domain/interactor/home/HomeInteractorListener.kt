package com.example.santantest.domain.interactor.home

import com.example.santantest.domain.model.StatementItem

interface HomeInteractorListener {

    fun onGetStatementsSuccess(statements: List<StatementItem>)
    fun onGetStatementsError()

}