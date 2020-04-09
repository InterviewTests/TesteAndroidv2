package pt.felipegouveia.bankapp.presentation.login

import pt.felipegouveia.bankapp.data.login.model.LoginBody
import pt.felipegouveia.bankapp.domain.interactors.LoginUseCase
import pt.felipegouveia.bankapp.presentation.BaseSchedulerProvider
import pt.felipegouveia.bankapp.presentation.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val schedulers: BaseSchedulerProvider,
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    fun login(networkAvailable: Boolean, loginBody: LoginBody) {
        loginUseCase.login(networkAvailable, loginBody)
    }

}