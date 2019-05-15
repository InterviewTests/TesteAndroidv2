package com.example.projetobank.util

import android.util.Log
import com.example.projetobank.data.model.Usuario
import com.example.projetobank.ui.login.LoginCampo

fun Usuario.ehValido(delegate: (loginCmapo: LoginCampo) -> Unit): Boolean{

    if(user.isEmpty()){
        Log.e("usuario ", user + "nome")
        delegate(LoginCampo.USUARIO)
        return false
    }
    if(password.isEmpty()){
        delegate(LoginCampo.SENHA)
        return false
    }

        if (!user.matches(".*[A-Z].*".toRegex()) ||!user.matches(".*[~!.......].*".toRegex()) ) {
            delegate(LoginCampo.USUARIO)
            return false
        }

    return true
}