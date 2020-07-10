package br.com.mdr.testeandroid.flow.signin

import android.content.Context
import br.com.mdr.testeandroid.model.business.User
import br.com.mdr.testeandroid.service.ISignInService

interface ISignInHandler {
    val signInPresenter: ISignInViewPresenter
    val service: ISignInService
    fun onUserNameChanged(userName: CharSequence)
    fun onPasswordChanged(password: CharSequence)
    fun getLocalUser(context: Context): User?
}
