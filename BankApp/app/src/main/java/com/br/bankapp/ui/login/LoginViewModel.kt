package com.br.bankapp.ui.login

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.br.bankapp.R
import com.br.bankapp.repository.BankRepository


class LoginViewModel : ViewModel() {
    private var repository: BankRepository = BankRepository()
    var loginListener: LoginListener? = null

    fun onLoginButtonClick(view: View) {
        val userInput = view.findViewById<EditText>(R.id.user_input)
        val pswInput = view.findViewById<EditText>(R.id.password_input)

        loginListener?.onStarted(view)
        if (userInput.text.isNullOrEmpty() && pswInput.text.isNullOrEmpty()) {
            loginListener?.onFailure(view, "Invalid email and password")
            return
        } else if (userInput.text.isNullOrEmpty()) {
            userInput.background = Drawable.createFromPath("@drawable/rounded_input_error")
            loginListener?.onFailure(view, "Invalid email")
            return
        } else if (pswInput.text.isNullOrEmpty()) {
            pswInput.background = Drawable.createFromPath("@drawable/rounded_input_error")
            loginListener?.onFailure(view, "Invalid password")
            return
        }
        val loginResponse = repository.login(
            mapOf(
                "user" to userInput.text.toString(),
                "password" to pswInput.text.toString()
            )
        )
        loginListener?.onSuccess(view, loginResponse)
    }
}