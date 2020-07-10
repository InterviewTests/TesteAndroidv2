package br.com.mdr.testeandroid.flow.signin

import br.com.mdr.testeandroid.extensions.isCPF
import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.api.UserApiModel
import br.com.mdr.testeandroid.service.SignInService
import br.com.mdr.testeandroid.util.MaskUtil

class SignInHandler(
    override val signInPresenter: SignInViewPresenter,
    override val service: SignInService
) : ISignInHandler {
    private var maskedCpf = false

    override fun onUserNameChanged(userName: CharSequence) {
        var userNameString = userName.toString()

        if (userNameString.length == 14 && maskedCpf) {
            userNameString = MaskUtil.removeMask(userName.toString())
        }

        if (userNameString.isCPF() && !maskedCpf) {
            maskedCpf = true
            signInPresenter.maskedUserName.value = MaskUtil.getCpfMask(userNameString)
        } else {
            maskedCpf = false
        }

        signInPresenter.userName = userNameString
        handleButtonState()
    }

    override fun onPasswordChanged(password: CharSequence) {
        signInPresenter.password = password.toString()
        handleButtonState()
    }

    private fun handleButtonState() {
        signInPresenter.handleButtonState()
    }

    suspend fun callSignInUser(): UserApiModel {
        var apiResult = UserApiModel()

        val signInApiModel = SignInApiModel(signInPresenter.userName, signInPresenter.password)
        service.loginUser(signInApiModel)?.let {
            signInPresenter.userName = ""
            signInPresenter.password = ""
            apiResult = it
        }
        return apiResult
    }
}
