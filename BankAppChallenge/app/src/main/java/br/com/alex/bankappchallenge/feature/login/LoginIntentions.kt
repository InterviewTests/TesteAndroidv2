package br.com.alex.bankappchallenge.feature.login

sealed class LoginIntentions {
    data class Login(
        val user: String,
        val password: String
    ) : LoginIntentions()

    object HasUser : LoginIntentions()
}