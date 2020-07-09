package br.com.mdr.testeandroid.flow.signin

import br.com.mdr.testeandroid.flow.main.LoadingPresenter
import br.com.mdr.testeandroid.model.business.User
import br.com.mdr.testeandroid.service.ISignInService

interface ISignInHandler {
    val loadingPresenter: LoadingPresenter
    val signInPresenter: ISignInViewPresenter
    val service: ISignInService
    fun onUserNameChanged(userName: CharSequence)
    fun onPasswordChanged(password: CharSequence)
    fun onSignInClicked()
    fun getLocalUser(): User?
}
