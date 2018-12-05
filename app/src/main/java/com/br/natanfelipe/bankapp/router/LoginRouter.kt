package com.br.natanfelipe.bankapp.router

import android.content.Intent
import com.br.natanfelipe.bankapp.interfaces.login.LoginRouterInput
import com.br.natanfelipe.bankapp.view.home.HomeActivity
import com.br.natanfelipe.bankapp.view.login.LoginActivity

class LoginRouter : LoginRouterInput {



    override fun determineNextScreen(loginActivity: LoginActivity): Intent {

        return Intent(loginActivity,HomeActivity::class.java)
    }
}