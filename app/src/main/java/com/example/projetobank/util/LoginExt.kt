package com.example.projetobank.util

import com.example.projetobank.data.model.Usuario
import com.example.projetobank.ui.login.LoginCampo

fun Usuario.ehValido(delegate: (loginCmapo: LoginCampo) -> Unit): Boolean {
     val pattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{3,}$"
    if (user.equals("")) {
        delegate(LoginCampo.USUARIO)
        return false
    }
    if (!password.matches(pattern.toRegex())) {
        delegate(LoginCampo.SENHA)
        return false
    }

    return true
}