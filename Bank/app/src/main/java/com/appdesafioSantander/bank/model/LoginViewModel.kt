package com.appdesafioSantander.bank.model

import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.appdesafioSantander.bank.repository.Repository
import com.appdesafioSantander.bank.ui.login.LoginListener
import com.appdesafioSantander.bank.ui.login.LoginValidate
import com.appdesafioSantander.bank.R


class LoginViewModel : ViewModel() {
    var repository: Repository = Repository()
    var loginListener: LoginListener? = null

    private val loginValidate = LoginValidate()

    fun onLoginButtonClick(view: View) {
        val userInput = view.findViewById<EditText>(R.id.user_input)
        val pswInput = view.findViewById<EditText>(R.id.password_input)

        loginListener?.onStarted(view)

        if (userInput.text.isEmpty()) {
            loginListener?.onFailure(view, "Preencha o nome de usuario")
            return
        } else if (!loginValidate.validEmail(userInput.text.toString())
            && !loginValidate.isCPF(userInput.text.toString())) {
            loginListener?.onFailure(view, "Email ou CPF invalido")
            return
        }
        else if (pswInput.text.isEmpty()) {
            loginListener?.onFailure(view, "Preencha a senha")
            return
        }
        else if(!loginValidate.validUpperCase(pswInput.text.toString())){
            loginListener?.onFailure(view, "A senha tem que ter menos uma letra maiúscula")
            return
        }
        else if(!loginValidate.validSpecialCharacter(pswInput.text.toString())){
            loginListener?.onFailure(view, "A senha tem que ter menos  um caracter especial")
            return
        }
        else if(!loginValidate.validAlphaNumeric(pswInput.text.toString())){
            loginListener?.onFailure(view, "A senha tem que ter lrtras e números")
            return
        }
        val loginResponse = repository.login(
            mapOf("user" to userInput.text.toString(), "password" to pswInput.text.toString())
        )
        loginListener?.onSuccess(view, loginResponse)
    }
}