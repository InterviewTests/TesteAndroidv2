package com.br.natanfelipe.bankapp.interfaces.login

import android.content.Intent
import com.br.natanfelipe.bankapp.view.login.LoginActivity

interface LoginRouterInput {

    fun determineNextScreen(loginActivity: LoginActivity): Intent
}


