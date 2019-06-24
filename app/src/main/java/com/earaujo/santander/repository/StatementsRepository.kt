package com.earaujo.santander.repository

import com.earaujo.santander.repository.models.StatementsResponse

interface StatementsRepository {
    fun fetchStatements(statementsRepositoryCallback: StatementsRepositoryCallback)
}

interface StatementsRepositoryCallback {
    fun onData(statementsResponse: Resource<StatementsResponse>)
}