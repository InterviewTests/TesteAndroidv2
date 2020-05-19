package br.com.bankapp.ui.login

import androidx.lifecycle.viewModelScope
import br.com.bankapp.commons.Loading
import br.com.bankapp.commons.Success
import br.com.bankapp.domain.usecases.LoginUseCase
import br.com.bankapp.ui.UiStateViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : UiStateViewModel() {

    fun executeAttemptLogin(user: String, password: String) {
        _uiState.value = Loading
        viewModelScope.launch(handler) {
            loginUseCase(user, password)
            _uiState.value = Success
        }
    }
}