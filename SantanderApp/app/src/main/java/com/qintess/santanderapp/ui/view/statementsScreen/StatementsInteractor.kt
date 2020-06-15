package com.qintess.santanderapp.ui.view.statementsScreen

import com.qintess.santanderapp.helper.Validator
import com.qintess.santanderapp.service.StatementsService

interface StatementsInteractorInput {
    fun fetchStatements(user_id: Int)
}

open class StatementsInteractor: StatementsInteractorInput {
    var output: StatementsPresenterInput? = null
    open var statementsService = StatementsService()

    override fun fetchStatements(user_id: Int) {
        statementsService?.getStatements(user_id,
            onSuccess = {
                output?.presentStatements(it)
            },
            onFailure = {
                output?.presentErrorMessage(Validator.STATEMENTS_TITLE_ERROR, "Tente novamente mais tarde")
            }
        )
    }
}