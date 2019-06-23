package com.earaujo.santander.statements

import com.earaujo.santander.repository.Resource
import com.earaujo.santander.repository.models.StatementsListModel
import com.earaujo.santander.repository.models.UserAccountModel
import java.lang.ref.WeakReference

class StatementsPresenter: StatementsPresenterInput {

    lateinit var statementsActivityInput: WeakReference<StatementsActivityInput>

    override fun presentLoading() {
        statementsActivityInput.get()?.displayUserData(Resource.loading(null))
    }

    override fun presentUserData(userAccount: UserAccountModel) {
        statementsActivityInput.get()?.displayUserData(Resource.success(userAccount))
    }

    override fun presentErrorMessage(errorMessage: String) {
        statementsActivityInput.get()?.displayUserData(Resource.error(errorMessage, null))
    }

    override fun presentLoginResponse(statementsList: List<StatementsListModel>) {
        statementsActivityInput.get()?.displayStatementsData(statementsList)
    }
}

interface StatementsPresenterInput {
    fun presentLoading()
    fun presentLoginResponse(statementsList: List<StatementsListModel>)
    fun presentErrorMessage(errorMessage: String)
    fun presentUserData(userAccount: UserAccountModel)
}