package br.com.crmm.bankapplication.framework.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.crmm.bankapplication.data.source.remote.state.LoginRemoteState
import br.com.crmm.bankapplication.domain.usecase.LoginUseCase
import br.com.crmm.bankapplication.extension.isNotValidEmail
import br.com.crmm.bankapplication.extension.isNotValidPassword
import br.com.crmm.bankapplication.extension.nonNullable
import br.com.crmm.bankapplication.framework.datasource.remote.dto.request.LoginRequestDTO
import br.com.crmm.bankapplication.framework.presentation.ui.common.AbstractViewModel
import br.com.crmm.bankapplication.framework.presentation.ui.extension.runOnUiThread
import br.com.crmm.bankapplication.framework.presentation.ui.login.state.LoginViewState
import io.reactivex.rxjava3.schedulers.Schedulers


class LoginViewModel(
    private val loginUseCase: LoginUseCase
): AbstractViewModel() {

    private val _loginInput = MutableLiveData<LoginRequestDTO>()
    val loginInput: LiveData<LoginRequestDTO> = _loginInput

    private val _loginState = MutableLiveData<LoginViewState>()
    val loginViewState: LiveData<LoginViewState> = _loginState

    init {
        this._loginInput.value = LoginRequestDTO()
    }

    fun performLogin(user: String, password: String){
        _loginInput.value = LoginRequestDTO(user, password)
        when {
            user.isNotValidEmail() -> _loginState.value = LoginViewState.InvalidInputUsernameViewState
            password.isNotValidPassword() -> _loginState.value = LoginViewState.InvalidInputPasswordViewState
            else -> {
                _loginState.value = LoginViewState.LoadingViewState
                executeAuth()
            }
        }
    }

    private fun executeAuth(){
        loginUseCase.execute(requireNotNull(_loginInput.value))
            .subscribeOn(Schedulers.io())
            .map {
                runOnUiThread {
                    _loginState.value = convertToViewStateFromRemoveState(it)
                }
            }
            .subscribe()
    }

    private fun convertToViewStateFromRemoveState(loginRemoteState: LoginRemoteState): LoginViewState{
        return when(loginRemoteState){
            is LoginRemoteState.SuccessfullyResponseState -> LoginViewState.SuccessfullyLoginState
            is LoginRemoteState.UnsuccessfullyResponseState -> {
                LoginViewState.UnsuccessfullyLoginState(
                    loginRemoteState.error.code.nonNullable(),
                    loginRemoteState.error.message.nonNullable()
                )
            }
            else -> LoginViewState.UnmappedErrorState
        }
    }
}