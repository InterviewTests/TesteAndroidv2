package com.earaujo.santander.statements

import android.content.Intent
import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.StatementsRepositoryCallback
import com.earaujo.santander.repository.StatementsRepositoryImpl
import com.earaujo.santander.repository.models.StatementsResponse
import com.earaujo.santander.repository.models.UserAccountModel

class StatementsInteractor : StatementsInteractorInput {

    lateinit var statementsPresenterInput: StatementsPresenterInput
    val statementsRepository = StatementsRepositoryImpl()

    override fun fetchUserData(intent: Intent) {
        val userAccount = intent.getSerializableExtra(StatementsActivity.INTENT_USER_DATA) as UserAccountModel
        statementsPresenterInput.presentUserData(userAccount)
    }

    override fun fetchStatements() {
        statementsRepository.fetchStatements(object: StatementsRepositoryCallback {
            override fun onData(statementsResponse: Resource<StatementsResponse>) {
                statementsPresenterInput.presentLoginResponse(statementsResponse)
            }
        })
    }

}

interface StatementsInteractorInput {
    fun fetchStatements()
    fun fetchUserData(intent: Intent)
}