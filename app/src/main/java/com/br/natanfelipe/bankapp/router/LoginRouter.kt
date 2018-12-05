package com.br.natanfelipe.bankapp.router

import android.content.Intent
import com.br.natanfelipe.bankapp.interfaces.login.LoginRouterInput
import com.br.natanfelipe.bankapp.view.home.HomeActivity
import com.br.natanfelipe.bankapp.view.login.LoginActivity
import com.br.natanfelipe.bankapp.view.login.LoginViewModel

class LoginRouter() : LoginRouterInput {


    //var activity: WeakReference<LoginActivity>? = null

    override fun determineNextScreen(loginActivity: LoginActivity): Intent {

        return Intent(loginActivity,HomeActivity::class.java)
    }


    override fun passDataToNextScene(loginViewModel: LoginViewModel, intent: Intent) {
        val userAccount = loginViewModel.userAccount
        intent.putExtra("userAccount",userAccount)
    }
}