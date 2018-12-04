package com.example.otavioaugusto.testesantander.login

import android.provider.Settings.Global.getString
import com.example.otavioaugusto.testesantander.R
import java.util.regex.Pattern

class LoginPresenter(val view:LoginActivity) : LoginContrato.Presenter {



    override fun validarCPF(cpf: String): Boolean {
        return Pattern.matches(
            """\d{3}\.\d{3}\.\d{3}-\d{2}""",
            cpf
        )
    }

    override fun validar(user: String, password: String): Boolean {
        var isValid = true

        if (user.isNullOrEmpty()) {
            isValid = false
            view.mensagensErro("Campo Vazio")
        } else
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
                if (!validarCPF(user)) {
                    isValid = false
                   view.mensagensErro("Digite o e-mail ou CPF do usuário")
                } else null
            } else null


        if (password.isNullOrEmpty()) {
            isValid = false
            view.mensagensErro("Campo Vazio")
        } else if (validarPassword(password)) {
            isValid = false
            view.mensagensErro("Padrão de senha não reconhecido")
        } else null


        return isValid
    }


    override fun validarPassword(password: String): Boolean {
        return Pattern.matches(
            """((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#${'$'}%]).{6,20})"""",
            password
        )
    }

}

