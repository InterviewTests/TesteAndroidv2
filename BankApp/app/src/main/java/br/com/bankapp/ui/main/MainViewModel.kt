package br.com.bankapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import br.com.bankapp.commons.Loading
import br.com.bankapp.commons.Success
import br.com.bankapp.domain.models.Statement
import br.com.bankapp.domain.models.UserAccount
import br.com.bankapp.domain.usecases.LoadStatementsUseCase
import br.com.bankapp.domain.usecases.GetStatementsUseCase
import br.com.bankapp.domain.usecases.GetUserAccountUseCase
import br.com.bankapp.ui.UiStateViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val loadStatementsUseCase: LoadStatementsUseCase,
    private val statementsUseCase: GetStatementsUseCase,
    private val userAccountUseCase: GetUserAccountUseCase
) : UiStateViewModel() {

    fun loadStatements(userId: Int, forceRefresh: Boolean) {
        if (!forceRefresh) _uiState.value = Loading
        viewModelScope.launch(handler) {
            loadStatementsUseCase(userId)
            _uiState.value = Success
        }
    }

    fun getStatements(userId: Int): LiveData<PagedList<Statement>> {
        return statementsUseCase(userId)
    }

    fun getUserAccount(userId: Int): LiveData<UserAccount> {
        return userAccountUseCase(userId)
    }
}