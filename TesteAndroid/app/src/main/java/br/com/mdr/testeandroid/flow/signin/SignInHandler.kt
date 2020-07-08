package br.com.mdr.testeandroid.flow.signin

import android.view.View
import br.com.mdr.testeandroid.extensions.isCPF
import br.com.mdr.testeandroid.extensions.isEmail
import br.com.mdr.testeandroid.extensions.scopeWithErrorHandling
import br.com.mdr.testeandroid.flow.error.ErrorListViewPresenter
import br.com.mdr.testeandroid.flow.main.LoadingPresenter
import br.com.mdr.testeandroid.model.api.SignInApiModel
import br.com.mdr.testeandroid.model.business.User
import br.com.mdr.testeandroid.service.ISignInService
import br.com.mdr.testeandroid.throwable.AppThrowable
import br.com.mdr.testeandroid.util.MaskUtil
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInHandler(
    override val loadingPresenter: LoadingPresenter,
    override val signInPresenter: ISignInViewPresenter,
    override val service: ISignInService,
    private var signInUser: User = User()
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
        signInPresenter.apply {
            if (canShowUserNameError && userNameWasFocused)
                hasError.postValue(!userNameString.isEmail() && !userNameString.isCPF())
        }
    }

    override fun onPasswordChanged(password: CharSequence) {
        signInPresenter.password = password.toString()
    }

    override fun onSignInClicked() {
        callSignInUser()
    }

    override fun onUserNameFocusChange() = View.OnFocusChangeListener { view, isFocused ->
        if (!signInPresenter.userNameWasFocused)
            signInPresenter.userNameWasFocused = isFocused

        if (!isFocused && !(view as TextInputEditText).text.isNullOrEmpty()) {
            signInPresenter.canShowUserNameError = true
            onUserNameChanged(view.text.toString())
        }
    }

    private fun callSignInUser() {
        loadingPresenter.showLoading()
        val scope = scopeWithErrorHandling(this::showError)
        scope.launch {
            val signInApiModel = SignInApiModel(signInPresenter.userName, signInPresenter.password)
            service.loginUser(signInApiModel)?.let {
                val response = it
                response.userAccount?.let { user ->
                    signInUser = user
                }

            }
            loadingPresenter.hideLoading()

            if (signInUser.userId != null) {
                GlobalScope.launch {
                    withContext(Dispatchers.Main) {
                        signInPresenter.userLive.value = signInUser
                        service.saveLoggedUser(signInUser)
                    }
                }
            }
        }
    }

    private fun showError(umaThrowable: AppThrowable) {
        loadingPresenter.hideLoading()
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                val presenter = ErrorListViewPresenter().withErrorList(umaThrowable.errors)
                signInPresenter.errorLive.value = presenter
                //signInPresenter.loginEnableChanged(true)
            }
        }
    }
}
