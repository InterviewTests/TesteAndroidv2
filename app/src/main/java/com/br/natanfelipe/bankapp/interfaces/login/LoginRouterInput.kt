package com.br.natanfelipe.bankapp.interfaces.login

import android.content.Intent
import com.br.natanfelipe.bankapp.view.login.LoginActivity
import com.br.natanfelipe.bankapp.view.login.LoginViewModel

interface LoginRouterInput {

    fun determineNextScreen(loginActivity: LoginActivity): Intent
    fun passDataToNextScene(loginViewModel: LoginViewModel, intent: Intent)
}




internal interface HomeRouterInput {
    fun determineNextScreen(position: Int): Intent
    fun passDataToNextScene(position: Int, intent: Intent)
}

