package com.br.natanfelipe.bankapp.presenter

import com.br.natanfelipe.bankapp.interfaces.home.HomePresenterInput
import com.br.natanfelipe.bankapp.interfaces.home.LoginActivityIntput
import com.br.natanfelipe.bankapp.interfaces.home.LoginPresenterInput
import com.br.natanfelipe.bankapp.model.UserAccount
import com.br.natanfelipe.bankapp.view.home.HomeResponse
import com.br.natanfelipe.bankapp.view.home.HomeViewModel
import com.br.natanfelipe.bankapp.view.login.LoginResponse
import com.br.natanfelipe.bankapp.view.login.LoginViewModel
import java.lang.ref.WeakReference

class LoginPresenter : LoginPresenterInput {

    lateinit var output : WeakReference<LoginActivityIntput>

    override fun presentLoginMetaData(response: LoginResponse) {
        var loginViewModel = LoginViewModel()

        loginViewModel.userAccount = UserAccount(response.userAccount.userId,response.userAccount.name,
                response.userAccount.bankAccount,response.userAccount.agency,
                response.userAccount.balance)
        output.get()?.displayLoginMetaData(loginViewModel)
    }
}