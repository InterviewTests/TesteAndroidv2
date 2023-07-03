package com.nandoligeiro.safrando.presentation.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nandoligeiro.safrando.domain.localUser.usecase.getLocalUser.GetLocalUserUseCase
import com.nandoligeiro.safrando.domain.login.result.LoginResult
import com.nandoligeiro.safrando.domain.login.usecase.postLogin.PostLoginUseCase
import com.nandoligeiro.safrando.presentation.login.mapper.LoginDomainToUiMapper
import com.nandoligeiro.safrando.presentation.login.model.UiLoginModel
import com.nandoligeiro.safrando.presentation.login.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase,
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val loginDomainToUiMapper: LoginDomainToUiMapper
) : ViewModel() {

    private val mutableLogin = MutableStateFlow<LoginState<UiLoginModel>>(LoginState.Default)
    val login: StateFlow<LoginState<UiLoginModel>> get() = mutableLogin

    private val mutableLocalUser = MutableStateFlow("")
    val localUser: StateFlow<String?> get() = mutableLocalUser
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

    fun getLocalUserSaved() {
        viewModelScope.launch {
            mutableLocalUser.value = getLocalUserUseCase().first()
        }
    }

}
