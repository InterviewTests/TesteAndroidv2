package br.com.crmm.bankapplication.framework.presentation.ui.statement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.crmm.bankapplication.data.state.LoginDataState
import br.com.crmm.bankapplication.data.state.StatementDataState
import br.com.crmm.bankapplication.domain.usecase.StatementUseCase
import br.com.crmm.bankapplication.extension.nonNullable
import br.com.crmm.bankapplication.framework.presentation.ui.common.AbstractViewModel
import br.com.crmm.bankapplication.framework.presentation.ui.extension.runOnUiThread
import br.com.crmm.bankapplication.framework.presentation.ui.login.state.LoginViewState
import br.com.crmm.bankapplication.framework.presentation.ui.statement.state.StatementViewState
import io.reactivex.rxjava3.schedulers.Schedulers

class StatementViewModel(
    private val statementUseCase: StatementUseCase
) : AbstractViewModel() {

    private val _statementDataState = MutableLiveData<StatementViewState>()
    val statementDataState: LiveData<StatementViewState> = _statementDataState

    fun fetch(userId: String){
        statementUseCase.execute(userId)
            .subscribeOn(Schedulers.io())
            .map {
                runOnUiThread {
                    _statementDataState.value = convertToViewStateFromRemoveState(it)
                }
            }
            .subscribe()
    }

    fun logout(){
        _statementDataState.value = StatementViewState.LogoutState
    }

    private fun convertToViewStateFromRemoveState(statementDataState: StatementDataState): StatementViewState {
        return when(statementDataState){
            is StatementDataState.SuccessfullyResponseState -> StatementViewState.SuccessfullyLoadState(
                statementDataState.statementDataResponseDTO
            )
            is StatementDataState.UnsuccessfullyResponseState -> {
                StatementViewState.UnsuccessfullyLoadState(
                    statementDataState.errorResponseDTO
                )
            }
            else -> StatementViewState.UnmappedErrorState
        }
    }
}
