package com.earaujo.santander.statements

import com.earaujo.santander.repository.StatementsRepositoryCallback
import com.earaujo.santander.repository.StatementsRepositoryFakeImpl
import com.earaujo.santander.repository.models.StatementsResponse

class StatementsInteractor : StatementsInteractorInput {

    lateinit var statementsPresenterInput: StatementsPresenterInput
    val statementsRepository = StatementsRepositoryFakeImpl()

    override fun fetchStatements() {
        statementsRepository.fetchStatements(object: StatementsRepositoryCallback {
            override fun onData(statementsResponse: StatementsResponse) {
                statementsPresenterInput.presentLoginResponse(statementsResponse.statementList)
            }
        })
    }
}

interface StatementsInteractorInput {
    fun fetchStatements()
}