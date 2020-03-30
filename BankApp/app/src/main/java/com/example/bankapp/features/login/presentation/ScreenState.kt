package com.example.bankapp.features.login.presentation

import com.example.bankapp.features.login.model.UserAccount

sealed class ScreenState {
    data class Login(val userAccount: UserAccount) : ScreenState()
    data class Error(val message: String = "Ops, tivemos um erro") : ScreenState()
}