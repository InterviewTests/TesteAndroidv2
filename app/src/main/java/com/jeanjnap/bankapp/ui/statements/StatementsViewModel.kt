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
    val onLogout: LiveData<Boolean> get() = _onLogout

    private val _statements = MutableLiveData<List<Statement>>()
    private val _onLogout = MutableLiveData<Boolean>()

    var userId: Long? = null
        set(value) {
            field = value
            fetchStatements()
        }

    fun onLogoutClick() {
        _onLogout.value = true
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
