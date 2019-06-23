package com.earaujo.santander.statements

import android.content.Intent
import com.earaujo.santander.repository.StatementsRepositoryCallback
import com.earaujo.santander.repository.StatementsRepositoryFakeImpl
import com.earaujo.santander.repository.models.StatementsResponse
import com.earaujo.santander.repository.models.UserAccountModel

class StatementsInteractor : StatementsInteractorInput {

    lateinit var statementsPresenterInput: StatementsPresenterInput
    val statementsRepository = StatementsRepositoryFakeImpl()

    override fun fetchUserData(intent: Intent) {
        val userAccount = intent.getSerializableExtra(StatementsActivity.INTENT_USER_DATA) as UserAccountModel
        statementsPresenterInput.presentUserData(userAccount)
    }

    override fun fetchStatements() {
        statementsPresenterInput.presentLoading()
        statementsRepository.fetchStatements(object: StatementsRepositoryCallback {
            override fun onData(statementsResponse: StatementsResponse) {
                statementsPresenterInput.presentLoginResponse(statementsResponse.statementList)
            }
        })
    }

}

interface StatementsInteractorInput {
    fun fetchStatements()
    fun fetchUserData(intent: Intent)
}