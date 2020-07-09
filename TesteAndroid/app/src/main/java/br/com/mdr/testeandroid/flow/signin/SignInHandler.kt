package br.com.mdr.testeandroid.flow.signin

import br.com.mdr.testeandroid.extensions.isCPF
import br.com.mdr.testeandroid.flow.main.LoadingPresenter
import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.business.User
import br.com.mdr.testeandroid.service.ISignInService
import br.com.mdr.testeandroid.util.MaskUtil
import kotlinx.coroutines.*

class SignInHandler(
    override val loadingPresenter: LoadingPresenter,
    override val signInPresenter: ISignInViewPresenter,
    override val service: ISignInService
) : ISignInHandler {
    private var maskedCpf = false

    override fun onUserNameChanged(userName: CharSequence) {
        var userNameString = userName.toString()

        if (userNameString.length == 14 && maskedCpf) {
            userNameString = MaskUtil.removeMask(userName.toString())
        }

        if (userNameString.isCPF() && !maskedCpf) {
            signInPresenter.maskedUserName.value = MaskUtil.getCpfMask(userNameString)
            maskedCpf = true
        }

        signInPresenter.userName = userNameString
        handleButtonState()
    }

    override fun onPasswordChanged(password: CharSequence) {
        signInPresenter.password = password.toString()
        handleButtonState()
    }

    override fun onSignInClicked() {
        callSignInUser()
    }

    override fun getLocalUser(): User? {
        return service.getLoggedUser()
    }

    private fun handleButtonState() {
        signInPresenter.handleButtonState()
    }

    private fun callSignInUser() {
        loadingPresenter.showLoading()
        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            val signInApiModel = SignInApiModel(signInPresenter.userName, signInPresenter.password)
            service.loginUser(signInApiModel)?.let { response ->
                GlobalScope.launch {
                    withContext(Dispatchers.Main) {
                        response.userAccount?.let { user ->
                            service.saveLoggedUser(user)
                            signInPresenter.userName = ""
                            signInPresenter.password = ""
                            signInPresenter.userLive.value = user
                        }
                        response.error?.let { responseError ->
                            signInPresenter.errorLive.value = responseError
                        }
                    }
                }

            }
            loadingPresenter.hideLoading()
        }
    }
}
