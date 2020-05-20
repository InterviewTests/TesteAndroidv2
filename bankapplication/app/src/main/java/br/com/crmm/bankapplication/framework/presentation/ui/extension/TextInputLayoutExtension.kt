package br.com.crmm.bankapplication.framework.presentation.ui.extension

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.hideError(){
    isErrorEnabled = false
}

fun TextInputLayout.invalidEmailOrCpf() {
    error = "Invalid format email or cpf"
}

fun TextInputLayout.invalidPassword() {
    error = "Invalid format password"
}