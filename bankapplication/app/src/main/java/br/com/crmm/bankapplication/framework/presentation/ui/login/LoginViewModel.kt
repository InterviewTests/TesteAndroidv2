package br.com.crmm.bankapplication.framework.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.crmm.bankapplication.framework.datasource.remote.dto.request.LoginRequestDTO
import br.com.crmm.bankapplication.domain.usecase.LoginUseCase
import br.com.crmm.bankapplication.extension.isNotValidEmail
import br.com.crmm.bankapplication.extension.isNotValidPassword
import br.com.crmm.bankapplication.framework.presentation.ui.common.AbstractViewModel
import br.com.crmm.bankapplication.framework.presentation.ui.login.state.LoginState


class LoginViewModel(
    private val loginUseCase: LoginUseCase
): AbstractViewModel() {

    private val _loginInput = MutableLiveData<LoginRequestDTO>()
    val loginInput: LiveData<LoginRequestDTO> = _loginInput

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> = _loginState

    init {
        this._loginInput.value = LoginRequestDTO()
    }

    fun performLogin(user: String, password: String){
        _loginInput.value = LoginRequestDTO(user, password)

        _loginState.value = when {
            user.isNotValidEmail() -> LoginState.InvalidInputUsernameState
            password.isNotValidPassword() -> LoginState.InvalidInputPasswordState
            else -> {
                loginUseCase.execute(requireNotNull(_loginInput.value))
                LoginState.LoadingState
            }
        }
    }
}