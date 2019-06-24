package com.earaujo.santander.statements

import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.models.StatementsResponse
import com.earaujo.santander.repository.models.UserAccountModel
import java.lang.ref.WeakReference

class StatementsPresenter: StatementsPresenterInput {

    lateinit var statementsActivityInput: WeakReference<StatementsActivityInput>

    override fun presentUserData(userAccount: UserAccountModel) {
        statementsActivityInput.get()?.displayUserData(userAccount)
    }

    override fun presentLoginResponse(statementsResponse: Resource<StatementsResponse>) {
        statementsActivityInput.get()?.displayStatementsData(statementsResponse)
    }
}

interface StatementsPresenterInput {
    fun presentLoginResponse(statementsResponse: Resource<StatementsResponse>)
    fun presentUserData(userAccount: UserAccountModel)
}