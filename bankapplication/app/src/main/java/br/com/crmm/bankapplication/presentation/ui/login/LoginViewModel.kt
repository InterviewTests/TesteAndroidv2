package br.com.crmm.bankapplication.presentation.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.crmm.bankapplication.data.source.remote.dto.request.LoginRequestDTO
import br.com.crmm.bankapplication.domain.usecase.LoginUseCase
import br.com.crmm.bankapplication.presentation.ui.common.AbstractViewModel

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): AbstractViewModel() {

    private val _loginInput = MutableLiveData<LoginRequestDTO>()
    val loginInput: LiveData<LoginRequestDTO> = _loginInput

    init {
        this._loginInput.value = LoginRequestDTO()
    }

    fun performLogin(user: String, password: String){
        _loginInput.value = LoginRequestDTO(user, password)
        loginUseCase.execute(requireNotNull(_loginInput.value))
    }
}