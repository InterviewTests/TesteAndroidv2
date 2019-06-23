package com.earaujo.santander.repository

import com.earaujo.santander.repository.models.StatementsListModel
import com.earaujo.santander.repository.models.StatementsResponse

class StatementsRepositoryFakeImpl: StatementsRepository {

    override fun fetchStatements(statementsRepositoryCallback: StatementsRepositoryCallback) {
        statementsRepositoryCallback.onData(StatementsResponse(
            listOf(StatementsListModel("", "", "", 1.0)),
            null)
        )
    }
}