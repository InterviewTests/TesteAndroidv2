package fingerfire.com.extractbank.features.statements.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fingerfire.com.extractbank.features.statements.data.StatementRepository
import fingerfire.com.extractbank.features.statements.ui.viewstate.StatementViewState
import fingerfire.com.extractbank.network.ServiceState
import kotlinx.coroutines.launch

class StatementViewModel(private val statementRepository: StatementRepository) : ViewModel() {
    private val statementMutableLiveData: MutableLiveData<StatementViewState> =
        MutableLiveData<StatementViewState>()

    val statementLiveData: LiveData<StatementViewState>
        get() = statementMutableLiveData

    fun getStatementsForUser(idUser: String) {
        viewModelScope.launch {
            when (val statementsResponse = statementRepository.getStatement(idUser)) {
                is ServiceState.Success -> {
                    val statements = statementsResponse.data
                    if (statements != null) {
                        if (statements.isNotEmpty()) {
                            statementMutableLiveData.postValue(StatementViewState.Success(statements))
                        } else {
                            statementMutableLiveData.postValue(StatementViewState.Error("Nenhum extrato encontrado."))
                        }
                    }
                }

                is ServiceState.Error -> {
                    statementMutableLiveData.postValue(StatementViewState.Error("Ocorreu um erro ao obter os extratos."))
                }
            }
        }
    }
}
