package com.accenture.primo.bankapp.ui.login.contracts

interface ILoginInteractor {
    fun login(user: String, password: String)
    fun readPreferences()
}