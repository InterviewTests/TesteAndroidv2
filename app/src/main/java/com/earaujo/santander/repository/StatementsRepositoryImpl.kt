package com.earaujo.santander.repository

import com.earaujo.santander.repository.models.StatementsResponse
import com.earaujo.santander.repository.rest.ApiUtil

class StatementsRepositoryImpl : StatementsRepository {

    override fun fetchStatements(statementsRepositoryCallback: StatementsRepositoryCallback) {
        ApiUtil().statements(object: ApiUtil.StatementsCallback {
            override fun onResponse(statementsResponse: Resource<StatementsResponse>) {
                statementsRepositoryCallback.onData(statementsResponse)
            }
        })
    }

}