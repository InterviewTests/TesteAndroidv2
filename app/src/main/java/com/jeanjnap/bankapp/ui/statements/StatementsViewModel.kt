package com.jeanjnap.bankapp.ui.statements

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jeanjnap.bankapp.ui.base.BaseViewModel
import com.jeanjnap.domain.entity.Response
import com.jeanjnap.domain.entity.Statement
import com.jeanjnap.domain.entity.SuccessResponse
import com.jeanjnap.domain.usecase.BankUseCase
import com.jeanjnap.infrastructure.network.Network

class StatementsViewModel(
    network: Network,
    private val bankUseCase: BankUseCase
) : BaseViewModel(network) {

    val statements: LiveData<List<Statement>> get() = _statements

    private val _statements = MutableLiveData<List<Statement>>()

    var userId: Long? = null
        set(value) {
            field = value
            fetchStatements()
        }

    private fun fetchStatements() {
        launchDataLoad {
            bankUseCase.getStatements(userId).handleStatementsResponse()
        }
    }

    private fun Response<List<Statement>>.handleStatementsResponse() {
        if (this is SuccessResponse) {
            _statements.value = body
        } else {
            displayError(resourcesString.genericError)
        }
    }
}
