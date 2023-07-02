package com.nandoligeiro.safrando.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandoligeiro.safrando.domain.login.result.LoginResult
import com.nandoligeiro.safrando.domain.login.usecase.postLogin.PostLoginUseCase
import com.nandoligeiro.safrando.presentation.login.mapper.LoginDomainToUiMapper
import com.nandoligeiro.safrando.presentation.login.model.UiLoginModel
import com.nandoligeiro.safrando.presentation.login.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase,
    private val loginDomainToUiMapper: LoginDomainToUiMapper
) : ViewModel() {

    private val mutableLogin = MutableStateFlow<LoginState<UiLoginModel>>(LoginState.Default)
    val login: StateFlow<LoginState<UiLoginModel>> get() = mutableLogin

    fun signIn(user: String, password: String) {

        viewModelScope.launch {
            when (val result = postLoginUseCase(user, password)) {
                is LoginResult.Success -> {
                    mutableLogin.value = LoginState.Success(
                        loginDomainToUiMapper.toUiLogin(result.data)
                    )
                }

                is LoginResult.Error -> {
                    mutableLogin.value = LoginState.Error
                }

                else -> {
                    mutableLogin.value = LoginState.CheckFieldError
                }

            }
        }
    }
}
