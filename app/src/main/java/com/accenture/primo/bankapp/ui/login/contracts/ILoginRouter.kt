package com.accenture.primo.bankapp.ui.login.contracts

import com.accenture.primo.bankapp.model.User

interface ILoginRouter {
    fun showNextScreen(user: User)
}