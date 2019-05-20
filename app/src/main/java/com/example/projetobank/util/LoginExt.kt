package com.example.projetobank.util

import android.util.Log
import com.example.projetobank.data.model.Usuario
import com.example.projetobank.ui.login.LoginCampo

fun Usuario.ehValido(delegate: (loginCmapo: LoginCampo) -> Unit): Boolean {

    if (user.equals("")) {
        delegate(LoginCampo.USUARIO)
        return false
    }
    if (!password.matches(".*[A-Z].*".toRegex()) && !password.matches(".*[~!.......].*".toRegex()) && !password.matches("[A-Za-z0-9]+".toRegex())) {
        delegate(LoginCampo.SENHA)
        return false
    }

    return true
}