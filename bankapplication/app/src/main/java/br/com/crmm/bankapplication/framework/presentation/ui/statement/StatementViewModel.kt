package br.com.crmm.bankapplication.framework.presentation.ui.statement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.crmm.bankapplication.data.state.StatementDataState
import br.com.crmm.bankapplication.domain.usecase.StatementUseCase
import br.com.crmm.bankapplication.framework.presentation.ui.common.AbstractViewModel
import br.com.crmm.bankapplication.framework.presentation.ui.extension.runOnUiThread
import io.reactivex.rxjava3.schedulers.Schedulers

class StatementViewModel(
    private val statementUseCase: StatementUseCase
) : AbstractViewModel() {

    private val _statementDataState = MutableLiveData<StatementDataState>()
    val statementDataState: LiveData<StatementDataState> = _statementDataState

    fun fetch(){
        statementUseCase.execute("1")
            .subscribeOn(Schedulers.io())
            .map {
                runOnUiThread {
                    _statementDataState.value = it
                    println("TEST -LIST - $it")
                }
            }
            .subscribe()
    }
}
