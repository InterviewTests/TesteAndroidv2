package br.com.alex.bankappchallenge.feature.login

sealed class LoginNavigation {
    object NavigateToStatement : LoginNavigation()
}