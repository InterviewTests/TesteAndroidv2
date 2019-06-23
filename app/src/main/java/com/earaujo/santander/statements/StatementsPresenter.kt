package com.earaujo.santander.statements

import com.earaujo.santander.repository.models.StatementsListModel
import java.lang.ref.WeakReference

class StatementsPresenter: StatementsPresenterInput {

    lateinit var statementsActivityInput: WeakReference<StatementsActivityInput>

    override fun presentLoginResponse(statementsList: List<StatementsListModel>) {
        statementsActivityInput.get()?.displayStatementsData(statementsList)
    }
}

interface StatementsPresenterInput {
    fun presentLoginResponse(statementsList: List<StatementsListModel>)
}